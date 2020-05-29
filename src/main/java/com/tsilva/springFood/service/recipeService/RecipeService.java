package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiClient.ApiConfig;
import com.tsilva.springFood.controller.apiClient.ResponseCallback;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.AnalyzedInstruction;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.ExtendedIngredient;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.Result;
import com.tsilva.springFood.controller.apiClient.request.get.GetBulkRecipeInformation;
import com.tsilva.springFood.controller.apiClient.request.get.GetRecipeSearch;
import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import com.tsilva.springFood.controller.apiServer.enums.recipeBaseSearch.SearchType;
import com.tsilva.springFood.dao.*;
import com.tsilva.springFood.entity.*;
import com.tsilva.springFood.events.findByRecipeNameCompletion.FindByRecipeNameCompletionEvent;
import com.tsilva.springFood.events.findByRecipeNameCompletion.FindByRecipeNameCompletionEventPublisher;
import com.tsilva.springFood.utils.TimeUtils;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.*;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Service
public class RecipeService implements IRecipeService
{
    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private GetRecipeSearch getRecipeSearch;

    @Autowired
    private GetBulkRecipeInformation getBulkRecipeInformation;

    @Autowired
    private IStepDao iStepDao;

    @Autowired
    private IRecipeSearchDao iRecipeSearchDao;

    @Autowired
    private IRecipeDetailDao iRecipeDetailDao;

    @Autowired
    private IRecipeBaseDao iRecipeBaseDao;

    @Autowired
    private IMeasureDao iMeasureDao;

    @Autowired
    private IIngredientMetasDao iIngredientMetasDao;

    @Autowired
    private IIngredientMetaInformationsDao iIngredientMetaInformationsDao;

    @Autowired
    private IIngredientDao iIngredientDao;

    @Autowired
    private IDishTypeDao iDishTypeDao;

    @Autowired
    private ICuisineDao iCuisineDao;

    @Autowired
    private FindByRecipeNameCompletionEventPublisher findByRecipeNameCompletionEventPublisher;

    @Override
    public void findByRecipeName(
            String recipeName,
            SearchType searchType,
            DeferredResult<RecipeBaseSearchResponse> deferredResult)
    {
        Date now = TimeUtils.now();

        RecipeSearch recipeSearch = iRecipeSearchDao.findByRecipeSearchName(recipeName);
        if(recipeSearch == null)
        {
            recipeSearch = new RecipeSearch(recipeName, now.getTime(), false);
        }
        else
        {
            if(recipeSearch.getSuccessfulIteration()
                    && !isOldSearch(now, TimeUtils.dateFromTimeStamp(recipeSearch.getUpdateTimeStamp())))
            {
                FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                        new FindByRecipeNameCompletionEvent(
                                this,
                                recipeName,
                                true,
                                0L,
                                0L,
                                searchType,
                                deferredResult);
                findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);

                LOG.debug("findByRecipeName() RecipeSearch not old enough to allow another search");
                return;
            }
        }
        iRecipeSearchDao.save(recipeSearch);

        List<RecipeBase> recipeBaseList = new LinkedList<>();
        findByRecipeName(
                recipeName, new Long[]{0L}, new Long[]{0L}, recipeBaseList, recipeSearch, searchType, deferredResult);
    }

    private boolean isOldSearch(@NonNull Date now, @NonNull Date recipeSearchDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(recipeSearchDate);
        calendar.add(Calendar.SECOND, ApiConfig.WAIT_AFTER_SUCCESSFUL_SEARCH_SECONDS);

        return calendar.getTime().before(now);
    }

    /**
     *
     * @param offset {@code new Long[]{0L}} to initialize
     * @param recipeAmount {@code new Long[]{0L}} to initialize
     */
    private void findByRecipeName(
            String recipeName,
            final Long[] offset,
            final Long[] recipeAmount,
            List<RecipeBase> recipeBaseList,
            RecipeSearch rs,
            SearchType searchType,
            DeferredResult<RecipeBaseSearchResponse> deferredResult)
    {
        getRecipeSearch.execute(
                recipeName,
                offset[0],
                new ResponseCallback<com.tsilva.springFood.controller.apiClient.contract.recipeSearch.RecipeSearch>()
        {
            @Override
            public void success(
                    com.tsilva.springFood.controller.apiClient.contract.recipeSearch.RecipeSearch recipeSearch)
            {
                long totalResults = 0L;
                if(recipeSearch.totalResults != null)
                {
                    totalResults = recipeSearch.totalResults;
                }
                else
                {
                    FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                            new FindByRecipeNameCompletionEvent(
                                    this,
                                    recipeName,
                                    false,
                                    offset[0],
                                    recipeAmount[0],
                                    searchType,
                                    deferredResult);
                    findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);

                    LOG.debug("findByRecipeName() couldn't find totalResults");
                    return;
                }

                if(recipeAmount[0] == 0L)
                {
                    recipeAmount[0] = totalResults;
                }

                Long rsOffset = rs.getOffset();
                if(!rs.getSuccessfulIteration() && offset[0] == 0L && rsOffset != null && rsOffset != 0L)
                {
                    offset[0] = rsOffset;

                    findByRecipeName(recipeName, offset, recipeAmount, recipeBaseList, rs, searchType, deferredResult);
                    LOG.debug("findByRecipeName() offset not 0. Starting from " + rsOffset);
                    return;
                }

                if(recipeSearch.results.isEmpty())
                {
                    FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                            new FindByRecipeNameCompletionEvent(
                                    this,
                                    recipeName,
                                    false,
                                    offset[0],
                                    recipeAmount[0],
                                    searchType,
                                    deferredResult);
                    findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);

                    LOG.debug("findByRecipeName() couldn't find any recipe with the name: " + recipeName);
                    return;
                }

                List<Long> resultIdList = new ArrayList<>(recipeSearch.results.size());
                Map<Long, Result> resultIdToResultMap = new HashMap<>();
                for(Result result: recipeSearch.results)
                {
                    resultIdList.add(result.id);
                    resultIdToResultMap.put(result.id, result);
                }

                getBulkRecipeInformation.execute(resultIdList, new ResponseCallback<List<RecipeInformation>>()
                {
                    @Override
                    public void success(List<RecipeInformation> recipeInformationList)
                    {
                        for(RecipeInformation recipeInformation: recipeInformationList)
                        {
                            offset[0]++;

                            // validate recipe information
                            IApiClientDataValidator iApiClientDataValidator = null;
                            try
                            {
                                iApiClientDataValidator = new ApiClientDataValidator(
                                        resultIdToResultMap.get(recipeInformation.id), recipeInformation);
                            }
                            catch(NullAttributeException e)
                            {
                                LOG.debug("findByRecipeName() IApiClientDataValidator null attribute(s)", e);
                            }

                            if(iApiClientDataValidator == null
                                    || !iApiClientDataValidator.validateResult()
                                    || !iApiClientDataValidator.validateRecipeInformation())
                            {
                                continue;
                            }

                            // persist data
                            RecipeBase recipeBase = saveRecipeBase(iApiClientDataValidator.getResult());

                            if(recipeBase == null)
                            {
                                continue;
                            }

                            RecipeDetail recipeDetail = saveRecipeDetail(
                                    recipeBase, iApiClientDataValidator.getRecipeInformation());

                            List<RecipeDetailDishTypes> recipeDetailDishTypesList = setRecipeDetailDishTypes(
                                    recipeDetail, iApiClientDataValidator.getRecipeInformation());

                            List<RecipeDetailCuisines> recipeDetailCuisines = setRecipeDetailCuisines(
                                    recipeDetail, iApiClientDataValidator.getRecipeInformation());

                            List<RecipeDetailIngredients> recipeDetailIngredientsList = setRecipeDetailIngredients(
                                    recipeDetail, iApiClientDataValidator.getRecipeInformation());

                            long recipeOptionId = getRecipeOptionId(recipeDetail);
                            List<RecipeDetailSteps> recipeDetailSteps = setRecipeDetailSteps(
                                    recipeDetail, iApiClientDataValidator.getRecipeInformation(), recipeOptionId);

                            iRecipeDetailDao.save(recipeDetail);

                            for(RecipeDetailIngredients recipeDetailIngredients: recipeDetailIngredientsList)
                            {
                                Ingredient ingredient = recipeDetailIngredients.getIngredient();
                                ExtendedIngredient extendedIngredient = ingredient.getExtendedIngredient();

                                List<IngredientMetas> ingredientMetas = setIngredientMetas(
                                        ingredient, extendedIngredient);

                                List<IngredientMetaInformations> ingredientMetaInformations =
                                        setIngredientMetaInformations(ingredient, extendedIngredient);

                                Measure measure = setMeasure(ingredient, extendedIngredient);

                                iIngredientDao.save(ingredient);
                            }

                            recipeBaseList.add(recipeBase);
                        }
                        if(offset[0] < recipeAmount[0])
                        {
                            findByRecipeName(
                                    recipeName, offset, recipeAmount, recipeBaseList, rs, searchType, deferredResult);
                        }
                        else    // all successfully update
                        {
                            rs.setUpdateTimeStamp(TimeUtils.now().getTime());
                            rs.setOffset(offset[0]);
                            rs.setSuccessfulIteration(true);

                            iRecipeSearchDao.save(rs);

                            LOG.debug("All was update successfully");

                            FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                                    new FindByRecipeNameCompletionEvent(
                                            this,
                                            recipeName,
                                            true,
                                            offset[0],
                                            recipeAmount[0],
                                            searchType,
                                            deferredResult);
                            findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);
                        }
                    }

                    @Override
                    public void failure(Throwable t)
                    {
                        rs.setUpdateTimeStamp(TimeUtils.now().getTime());
                        rs.setOffset(offset[0]);
                        rs.setSuccessfulIteration(false);

                        iRecipeSearchDao.save(rs);

                        LOG.debug("GetBulkRecipeInformation.execute().failure()", t);

                        FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                                new FindByRecipeNameCompletionEvent(
                                        this,
                                        recipeName,
                                        false,
                                        offset[0],
                                        recipeAmount[0],
                                        searchType,
                                        deferredResult);
                        findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);
                    }
                });
            }

            @Override
            public void failure(Throwable t)
            {
                if(offset[0] != 0)
                {
                    rs.setUpdateTimeStamp(TimeUtils.now().getTime());
                    rs.setOffset(offset[0]);
                    rs.setSuccessfulIteration(false);

                    iRecipeSearchDao.save(rs);
                }

                LOG.debug("GetRecipeSearch.execute().failure()", t);

                FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent =
                        new FindByRecipeNameCompletionEvent(
                                this,
                                recipeName,
                                false,
                                offset[0],
                                recipeAmount[0],
                                searchType,
                                deferredResult);
                findByRecipeNameCompletionEventPublisher.publishEvent(findByRecipeNameCompletionEvent);
            }
        });
    }

    /**
     *
     * @return {@code null} if recipe already exists
     */
    @Nullable
    private RecipeBase saveRecipeBase(@NonNull Result result)
    {
        // recipe already exists
        if(iRecipeBaseDao.findBySpoonacularId(result.id) != null)
        {
            LOG.debug("saveRecipeBase() recipe already exists");
            return null;
        }

        Long id = result.id;
        String title = result.title;
        Long readyInMinutes = null;
        if(result.readyInMinutes != null)
        {
            readyInMinutes = result.readyInMinutes;
        }
        Long servings = null;
        if(result.servings != null)
        {
            servings = result.servings;
        }
        String image = null;
        if(result.image != null)
        {
            image = result.image;
        }

        RecipeBase recipeBase = new RecipeBase(id, title, readyInMinutes, servings, image, TimeUtils.now().getTime());

        iRecipeBaseDao.save(recipeBase);

        return recipeBase;
    }

    private RecipeDetail saveRecipeDetail(@NonNull RecipeBase recipeBase, @NonNull RecipeInformation recipeInformation)
    {
        Boolean vegetarian = null;
        if(recipeInformation.vegetarian != null)
        {
            vegetarian = recipeInformation.vegetarian;
        }
        Boolean vegan = null;
        if(recipeInformation.vegan != null)
        {
            vegan = recipeInformation.vegan;
        }
        Boolean glutenFree = null;
        if(recipeInformation.glutenFree != null)
        {
            glutenFree = recipeInformation.glutenFree;
        }
        Boolean dairyFree = null;
        if(recipeInformation.dairyFree != null)
        {
            dairyFree = recipeInformation.dairyFree;
        }
        Boolean veryHealthy = null;
        if(recipeInformation.veryHealthy != null)
        {
            veryHealthy = recipeInformation.veryHealthy;
        }
        Boolean cheap = null;
        if(recipeInformation.cheap != null)
        {
            cheap = recipeInformation.cheap;
        }
        Boolean veryPopular = null;
        if(recipeInformation.veryPopular != null)
        {
            veryPopular = recipeInformation.veryPopular;
        }
        Boolean sustainable = null;
        if(recipeInformation.sustainable != null)
        {
            sustainable = recipeInformation.sustainable;
        }
        Boolean lowFodmap = null;
        if(recipeInformation.lowFodmap != null)
        {
            lowFodmap = recipeInformation.lowFodmap;
        }
        Long weightWatcherSmartPoints = null;
        if(recipeInformation.weightWatcherSmartPoints != null)
        {
            weightWatcherSmartPoints = recipeInformation.weightWatcherSmartPoints;
        }
        String gaps = null;
        if(recipeInformation.gaps != null)
        {
            gaps = recipeInformation.gaps;
        }
        Long preparationMinutes = null;
        if(recipeInformation.preparationMinutes != null)
        {
            preparationMinutes = recipeInformation.preparationMinutes;
        }
        Long cookingMinutes = null;
        if(recipeInformation.cookingMinutes != null)
        {
            cookingMinutes = recipeInformation.cookingMinutes;
        }
        String sourceUrl = null;
        if(recipeInformation.sourceUrl != null)
        {
            sourceUrl = recipeInformation.sourceUrl;
        }
        String spoonacularSourceUrl = null;
        if(recipeInformation.spoonacularSourceUrl != null)
        {
            spoonacularSourceUrl = recipeInformation.spoonacularSourceUrl;
        }
        Long aggregateLikes = null;
        if(recipeInformation.aggregateLikes != null)
        {
            aggregateLikes = recipeInformation.aggregateLikes;
        }
        Double spoonacularScore = null;
        if(recipeInformation.spoonacularScore != null)
        {
            spoonacularScore = recipeInformation.spoonacularScore;
        }
        Double healthScore = null;
        if(recipeInformation.healthScore != null)
        {
            healthScore = recipeInformation.healthScore;
        }
        String creditsText = null;
        if(recipeInformation.creditsText != null)
        {
            creditsText = recipeInformation.creditsText;
        }
        String sourceName = null;
        if(recipeInformation.sourceName != null)
        {
            sourceName = recipeInformation.sourceName;
        }
        Double pricePerServing = null;
        if(recipeInformation.pricePerServing != null)
        {
            pricePerServing = recipeInformation.pricePerServing;
        }
        String title = null;
        if(recipeInformation.title != null)
        {
            title = recipeInformation.title;
        }
        Long readyInMinutes = null;
        if(recipeInformation.readyInMinutes != null)
        {
            readyInMinutes = recipeInformation.readyInMinutes;
        }
        Long servings = null;
        if(recipeInformation.servings != null)
        {
            servings = recipeInformation.servings;
        }
        String image = null;
        if(recipeInformation.image != null)
        {
            image = recipeInformation.image;
        }
        String imageType = null;
        if(recipeInformation.imageType != null)
        {
            imageType = recipeInformation.imageType;
        }
        String summary = null;
        if(recipeInformation.summary != null)
        {
            summary = recipeInformation.summary;
        }
        String instructions = null;
        if(recipeInformation.instructions != null)
        {
            instructions = recipeInformation.instructions;
        }
        Long originalId = null;
        if(recipeInformation.originalId != null)
        {
            originalId = recipeInformation.originalId;
        }

        RecipeDetail recipeDetail = new RecipeDetail(vegetarian, vegan, glutenFree, dairyFree, veryHealthy, cheap,
                veryPopular, sustainable, lowFodmap, weightWatcherSmartPoints, gaps, preparationMinutes, cookingMinutes,
                sourceUrl, spoonacularSourceUrl, aggregateLikes, spoonacularScore, healthScore, creditsText, sourceName,
                pricePerServing, title, readyInMinutes, servings, image, imageType, summary, instructions, originalId,
                recipeBase);
        iRecipeDetailDao.save(recipeDetail);

        return recipeDetail;
    }

    private List<RecipeDetailDishTypes> setRecipeDetailDishTypes(
            @NonNull RecipeDetail recipeDetail,
            @NonNull RecipeInformation recipeInformation)
    {
        List<RecipeDetailDishTypes> recipeDetailDishTypesList = new LinkedList<>();
        for(String dishType: recipeInformation.dishTypes)
        {
            if(dishType != null)
            {
                recipeDetailDishTypesList.add(new RecipeDetailDishTypes(recipeDetail, new DishType(dishType)));
            }
        }
        recipeDetail.setRecipeDetailDishTypes(recipeDetailDishTypesList);

        return recipeDetailDishTypesList;
    }

    private List<RecipeDetailCuisines> setRecipeDetailCuisines(
            @NonNull RecipeDetail recipeDetail,
            @NonNull RecipeInformation recipeInformation)
    {
        List<RecipeDetailCuisines> recipeDetailCuisines = new LinkedList<>();
        for(String cuisine: recipeInformation.cuisines)
        {
            if(cuisine != null)
            {
                recipeDetailCuisines.add(new RecipeDetailCuisines(recipeDetail, new Cuisine(cuisine)));
            }
        }
        recipeDetail.setRecipeDetailCuisines(recipeDetailCuisines);

        return recipeDetailCuisines;
    }

    private List<RecipeDetailIngredients> setRecipeDetailIngredients(
            @NonNull RecipeDetail recipeDetail,
            @NonNull RecipeInformation recipeInformation)
    {
        List<RecipeDetailIngredients> recipeDetailIngredients = new LinkedList<>();
        for(ExtendedIngredient extendedIngredient: recipeInformation.extendedIngredients)
        {
            Long id = null;
            if(extendedIngredient.id != null)
            {
                id = extendedIngredient.id;
            }
            String aisle = null;
            if(extendedIngredient.aisle != null)
            {
                aisle = extendedIngredient.aisle;
            }
            String image = null;
            if(extendedIngredient.image != null)
            {
                image = extendedIngredient.image;
            }
            String consistency = null;
            if(extendedIngredient.consistency != null)
            {
                consistency = extendedIngredient.consistency;
            }
            String name = extendedIngredient.name;
            String original = null;
            if(extendedIngredient.original != null)
            {
                original = extendedIngredient.original;
            }
            String originalString = null;
            if(extendedIngredient.originalString != null)
            {
                originalString = extendedIngredient.originalString;
            }
            String originalName = null;
            if(extendedIngredient.originalName != null)
            {
                originalName = extendedIngredient.originalName;
            }
            Double amount = null;
            if(extendedIngredient.amount != null)
            {
                amount = extendedIngredient.amount;
            }
            String unit = null;
            if(extendedIngredient.unit != null)
            {
                unit = extendedIngredient.unit;
            }

            recipeDetailIngredients.add(
                    new RecipeDetailIngredients(recipeDetail,
                            new Ingredient(id, aisle, image, consistency, name, original, originalString, originalName,
                                    amount, unit, extendedIngredient)));
        }
        recipeDetail.setRecipeDetailIngredients(recipeDetailIngredients);

        return recipeDetailIngredients;
    }

    private long getRecipeOptionId(@NonNull RecipeDetail recipeDetail)
    {
        long recipeOptionId = 0;
        Collection<RecipeDetailSteps> recipeDetailStepsCollection = recipeDetail.getRecipeDetailSteps();
        if(recipeDetailStepsCollection != null)
        {
            for(RecipeDetailSteps recipeDetailSteps: recipeDetailStepsCollection)
            {
                recipeOptionId = (recipeDetailSteps.getRecipeOptionId() > recipeOptionId)
                        ? recipeDetailSteps.getRecipeOptionId() : recipeOptionId;
            }
        }

        return recipeOptionId;
    }

    private List<RecipeDetailSteps> setRecipeDetailSteps(
            @NonNull RecipeDetail recipeDetail,
            @NonNull RecipeInformation recipeInformation,
            long recipeOptionId)
    {
        List<RecipeDetailSteps> recipeDetailSteps = new LinkedList<>();
        for(AnalyzedInstruction analyzedInstruction: recipeInformation.analyzedInstructions)
        {
            for(com.tsilva.springFood.controller.apiClient.contract.recipeInformation.Step step:
                    analyzedInstruction.steps)
            {
                Long lengthNumber = null;
                String lengthUnit = null;
                if(step.length != null && (step.length.number != null && step.length.unit != null))
                {
                    lengthNumber = step.length.number;
                    lengthUnit = step.length.unit;
                }
                recipeDetailSteps.add(new RecipeDetailSteps(recipeOptionId, recipeDetail,
                        new Step(step.number, step.step, lengthNumber, lengthUnit)));
            }
            recipeOptionId++;
        }
        recipeDetail.setRecipeDetailSteps(recipeDetailSteps);

        return recipeDetailSteps;
    }

    private List<IngredientMetas> setIngredientMetas(
            @NonNull Ingredient ingredient,
            @NonNull ExtendedIngredient extendedIngredient)
    {
        List<IngredientMetas> ingredientMetas = new LinkedList<>();
        for(String meta: extendedIngredient.meta)
        {
            if(meta != null)
            {
                ingredientMetas.add(new IngredientMetas(meta, ingredient));
            }
        }
        ingredient.setIngredientMetas(ingredientMetas);

        return ingredientMetas;
    }

    private List<IngredientMetaInformations> setIngredientMetaInformations(
            @NonNull Ingredient ingredient,
            @NonNull ExtendedIngredient extendedIngredient)
    {
        List<IngredientMetaInformations> ingredientMetaInformations = new LinkedList<>();
        for(String metaInformation: extendedIngredient.metaInformation)
        {
            if(metaInformation != null)
            {
                ingredientMetaInformations.add(new IngredientMetaInformations(metaInformation, ingredient));
            }
        }
        ingredient.setIngredientMetaInformations(ingredientMetaInformations);

        return ingredientMetaInformations;
    }

    /**
     *
     * @return {@code null} if data is not valid
     */
    @Nullable
    private Measure setMeasure(@NonNull Ingredient ingredient, @NonNull ExtendedIngredient extendedIngredient)
    {
        if(extendedIngredient.measures == null)
        {
            return null;
        }

        String unitShort = null;
        if(extendedIngredient.measures.metric != null && extendedIngredient.measures.metric.unitShort != null)
        {
            unitShort = extendedIngredient.measures.metric.unitShort;
        }
        String unitLong = null;
        if(extendedIngredient.measures.metric != null && extendedIngredient.measures.metric.unitLong != null)
        {
            unitLong = extendedIngredient.measures.metric.unitLong;
        }
        String usUnitShort = null;
        if(extendedIngredient.measures.us != null && extendedIngredient.measures.us.unitShort != null)
        {
            usUnitShort = extendedIngredient.measures.us.unitShort;
        }
        String usUnitLong = null;
        if(extendedIngredient.measures.us != null && extendedIngredient.measures.us.unitLong != null)
        {
            usUnitLong = extendedIngredient.measures.us.unitLong;
        }

        Measure measure = new Measure(unitShort, unitLong, usUnitShort, usUnitLong, ingredient);

        ingredient.setMeasure(measure);

        return measure;
    }
}
