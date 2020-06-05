package com.tsilva.springFood.controller.apiServer;

import com.tsilva.springFood.controller.apiServer.contract.RecipeDeleteResponse;
import com.tsilva.springFood.controller.apiServer.contract.RecipeSaveResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse.RecipeDetailResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse.RecipeDetailSearchResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.RecipeSaveRequest;
import com.tsilva.springFood.controller.apiServer.enums.recipeBaseSearch.SearchType;
import com.tsilva.springFood.dao.IIngredientDao;
import com.tsilva.springFood.dao.IRecipeBaseDao;
import com.tsilva.springFood.dao.IRecipeDetailDao;
import com.tsilva.springFood.entity.RecipeBase;
import com.tsilva.springFood.entity.RecipeDetail;
import com.tsilva.springFood.events.findByRecipeNameCompletion.FindByRecipeNameCompletionEvent;
import com.tsilva.springFood.service.recipeService.IRecipeService;
import com.tsilva.springFood.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@RestController
@RequestMapping("/spring-food-api")
public class ApiControllerServer implements ApplicationListener<FindByRecipeNameCompletionEvent>
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiControllerServer.class);

    @Autowired
    private IRecipeService iRecipeService;

    @Autowired
    private IRecipeBaseDao iRecipeBaseDao;

    @Autowired
    private IRecipeDetailDao iRecipeDetailDao;

    @Autowired
    private IIngredientDao iIngredientDao;

    /**
     *
     * @param searchTypeInt if 1 will use recipeName as ingredient name for the search instead
     * @return
     */
    @RequestMapping(value = "/recipes/{recipeName}", method = RequestMethod.GET)
    public DeferredResult<RecipeBaseSearchResponse> recipeMapping(
            @PathVariable(name = "recipeName") String recipeName,
            @RequestParam(name = "searchType", required = false) Integer searchTypeInt)
    {
        DeferredResult<RecipeBaseSearchResponse> deferredResult = new DeferredResult<>(300000L);

        SearchType searchType = SearchType.TITLE;
        if(searchTypeInt != null && searchTypeInt == 1)
        {
            searchType = SearchType.INGREDIENT;
        }

        iRecipeService.findByRecipeName(recipeName, searchType, deferredResult);

        return deferredResult;
    }

    @RequestMapping(value = "/recipeDetail/{recipeBaseId}", method = RequestMethod.GET)
    public RecipeDetailSearchResponse recipeMapping(@PathVariable(name = "recipeBaseId") Long recipeBaseId)
    {
        Date now = TimeUtils.now();

        RecipeDetail recipeDetail = iRecipeDetailDao.findByRecipeBaseIdAllLoaded(recipeBaseId);

        RecipeDetailSearchResponse recipeDetailSearchResponse = null;
        if(recipeDetail == null)
        {
            // send error response, no data found
            recipeDetailSearchResponse = new RecipeDetailSearchResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Recipe detail not found for recipeBaseId: " + recipeBaseId,
                    now.getTime(),
                    null);

            LOG.debug("Recipe detail not found for recipeBaseId: " + recipeBaseId);
        }
        else
        {
            recipeDetailSearchResponse = new RecipeDetailSearchResponse(
                    HttpStatus.OK.value(),
                    "Result found for recipeBaseId: " + recipeBaseId,
                    now.getTime(),
                    new RecipeDetailResponse(recipeDetail));
        }

        return recipeDetailSearchResponse;
    }

    @RequestMapping(value = "/recipeDelete/{recipeBaseId}", method = RequestMethod.DELETE)
    public RecipeDeleteResponse recipeDeleteMapping(@PathVariable(name = "recipeBaseId") Long recipeBaseId)
    {
        Date now = TimeUtils.now();

        RecipeBase recipeBase = iRecipeBaseDao.findById(recipeBaseId);
        RecipeDeleteResponse recipeDeleteResponse = null;
        if(recipeBase == null)
        {
            // send error response, no data found
            recipeDeleteResponse = new RecipeDeleteResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Recipe not found for recipeBaseId: " + recipeBaseId,
                    now.getTime());

            LOG.debug("Recipe not found for recipeBaseId: " + recipeBaseId);
        }
        else
        {
            iRecipeBaseDao.delete(recipeBase);

            recipeDeleteResponse = new RecipeDeleteResponse(
                    HttpStatus.OK.value(),
                    "Deleted recipe with recipeBaseId: " + recipeBaseId,
                    now.getTime());
        }

        return recipeDeleteResponse;
    }

    @RequestMapping(value = "/recipeSave", method = RequestMethod.POST)
    public RecipeSaveResponse recipeSaveMapping(@RequestBody(required = false) RecipeSaveRequest recipeSaveRequest)
    {
        Date now = TimeUtils.now();

        RecipeSaveResponse recipeSaveResponse = null;
        if(iRecipeService.saveRecipe(recipeSaveRequest))
        {
            // send OK status
            recipeSaveResponse = new RecipeSaveResponse(
                    HttpStatus.OK.value(),
                    "Recipe successfully saved.",
                    now.getTime());

            LOG.debug("Recipe successfully saved.");
        }
        else
        {
            // send error status
            recipeSaveResponse = new RecipeSaveResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Recipe save failed.",
                    now.getTime());

            LOG.debug("Recipe save failed.");
        }

        return recipeSaveResponse;
    }

    @Override
    public void onApplicationEvent(FindByRecipeNameCompletionEvent event)
    {
        Date now = TimeUtils.now();

        String recipeName = event.getName();
        SearchType searchType = event.getSearchType();
        DeferredResult<RecipeBaseSearchResponse> deferredResult = event.getDeferredResult();

        List<RecipeBase> recipeBaseList = null;
        if(searchType == SearchType.INGREDIENT)
        {
            recipeBaseList = iIngredientDao.findRecipeBaseListByIngredientNameLike(recipeName);
        }
        else
        {
            recipeBaseList = iRecipeBaseDao.findRecipesByNameLike(recipeName);
        }
        RecipeBaseSearchResponse recipeBaseSearchResponse = null;
        if(recipeBaseList == null || recipeBaseList.isEmpty())
        {
            // send error response, no data found
            recipeBaseSearchResponse = new RecipeBaseSearchResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "No recipe was found with the name: " + recipeName,
                    now.getTime(),
                    null);

            LOG.debug("No recipe was found with the name: " + recipeName);
        }
        else
        {
            List<RecipeBaseResponse> recipeBaseResponseList = new ArrayList<>(recipeBaseList.size());
            for(RecipeBase recipeBase: recipeBaseList)
            {
                if(recipeBase != null)
                {
                    RecipeBaseResponse recipeBaseResponse = new RecipeBaseResponse(recipeBase);
                    if(recipeBaseResponse.getId() != null)
                    {
                        recipeBaseResponseList.add(recipeBaseResponse);
                    }
                }
            }

            recipeBaseSearchResponse = new RecipeBaseSearchResponse(
                    HttpStatus.OK.value(),
                    "Result found for: " + recipeName,
                    now.getTime(),
                    recipeBaseResponseList);
        }

        deferredResult.setResult(recipeBaseSearchResponse);
    }
}