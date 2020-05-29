package com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse;

import com.tsilva.springFood.entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Telmo Silva on 26.05.2020.
 */

public class RecipeDetailResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230026L;

    private Long id = null;
    private Boolean vegetarian = null;
    private Boolean vegan = null;
    private Boolean glutenFree = null;
    private Boolean dairyFree = null;
    private Boolean veryHealthy = null;
    private Boolean cheap = null;
    private Boolean veryPopular = null;
    private Boolean sustainable = null;
    private Boolean lowFodmap = null;
    private Long weightWatcherSmartPoints = null;
    private String gaps = null;
    private Long preparationInMinutes = null;
    private Long cookingMinutes = null;
    private String sourceUrl = null;
    private String spoonacularSourceUrl = null;
    private Long aggregateLikes = null;
    private Double spoonacularScore = null;
    private Double healthScore = null;
    private String creditsText = null;
    private String sourceName = null;
    private Double pricePerServing = null;
    private String title = null;
    private Long readyInMinutes = null;
    private Long servings = null;
    private String image = null;
    private String imageType = null;
    private String summary = null;
    private String instructions = null;
    private Long originalId = null;
    private List<String> dishTypes = null;
    private List<String> cuisines = null;
    private List<List<StepResponse>> stepOptionList = null;
    private List<IngredientResponse> ingredients = null;

    public RecipeDetailResponse(RecipeDetail recipeDetail)
    {
        if(recipeDetail == null)
        {
            return;
        }

        this.id = recipeDetail.getId();
        this.vegetarian = recipeDetail.getVegetarian();
        this.vegan = recipeDetail.getVegan();
        this.glutenFree = recipeDetail.getGlutenFree();
        this.dairyFree = recipeDetail.getDairyFree();
        this.veryHealthy = recipeDetail.getVeryHealthy();
        this.cheap = recipeDetail.getCheap();
        this.veryPopular = recipeDetail.getVeryPopular();
        this.sustainable = recipeDetail.getSustainable();
        this.lowFodmap = recipeDetail.getLowFodmap();
        this.weightWatcherSmartPoints = recipeDetail.getWeightWatcherSmartPoints();
        this.gaps = recipeDetail.getGaps();
        this.preparationInMinutes = recipeDetail.getPreparationInMinutes();
        this.cookingMinutes = recipeDetail.getCookingMinutes();
        this.sourceUrl = recipeDetail.getSourceUrl();
        this.spoonacularSourceUrl = recipeDetail.getSpoonacularSourceUrl();
        this.aggregateLikes = recipeDetail.getAggregateLikes();
        this.spoonacularScore = recipeDetail.getSpoonacularScore();
        this.healthScore = recipeDetail.getHealthScore();
        this.creditsText = recipeDetail.getCreditsText();
        this.sourceName = recipeDetail.getSourceName();
        this.pricePerServing = recipeDetail.getPricePerServing();
        this.title = recipeDetail.getTitle();
        this.readyInMinutes = recipeDetail.getReadyInMinutes();
        this.servings = recipeDetail.getServings();
        this.image = recipeDetail.getImage();
        this.imageType = recipeDetail.getImageType();
        this.summary = recipeDetail.getSummary();
        this.instructions = recipeDetail.getInstructions();
        this.originalId = recipeDetail.getOriginalId();

        Collection<RecipeDetailDishTypes> recipeDetailDishTypesCollection = recipeDetail.getRecipeDetailDishTypes();
        if(recipeDetailDishTypesCollection != null && !recipeDetailDishTypesCollection.isEmpty())
        {
            this.dishTypes = new ArrayList<>(recipeDetailDishTypesCollection.size());
            for(RecipeDetailDishTypes recipeDetailDishTypes: recipeDetailDishTypesCollection)
            {
                this.dishTypes.add(recipeDetailDishTypes.getDishType().getName());
            }
        }

        Collection<RecipeDetailCuisines> recipeDetailCuisinesCollection = recipeDetail.getRecipeDetailCuisines();
        if(recipeDetailCuisinesCollection != null && !recipeDetailCuisinesCollection.isEmpty())
        {
            this.cuisines = new ArrayList<>(recipeDetailCuisinesCollection.size());
            for(RecipeDetailCuisines recipeDetailCuisines: recipeDetailCuisinesCollection)
            {
                this.cuisines.add(recipeDetailCuisines.getCuisine().getName());
            }
        }

        Collection<RecipeDetailSteps> recipeDetailStepsCollection = recipeDetail.getRecipeDetailSteps();
        if(recipeDetailStepsCollection != null && !recipeDetailStepsCollection.isEmpty())
        {
            // get amount of step options for this recipe
            int stepOptions = -1;
            for(RecipeDetailSteps recipeDetailSteps: recipeDetailStepsCollection)
            {
                Long recipeOptionId = recipeDetailSteps.getRecipeOptionId();
                if(recipeOptionId != null)
                {
                    if(recipeOptionId > Integer.MAX_VALUE)
                    {
                        recipeOptionId = (long) Integer.MAX_VALUE;
                    }
                    stepOptions = Math.max(stepOptions, recipeOptionId.intValue());
                }
            }
            ++stepOptions;

            // populate list with steps list by option
            if(stepOptions != 0)
            {
                this.stepOptionList = new ArrayList<>((int) stepOptions);
                int i = 0;
                while(stepOptions < Integer.MAX_VALUE && i < stepOptions)
                {
                    for(RecipeDetailSteps recipeDetailSteps: recipeDetailStepsCollection)
                    {
                        if(recipeDetailSteps.getRecipeOptionId() == i)
                        {
                            if(this.stepOptionList.isEmpty() || this.stepOptionList.size() < i + 1)
                            {
                                this.stepOptionList.add(new LinkedList<>());
                            }
                            this.stepOptionList.get(i).add(new StepResponse(recipeDetailSteps.getStep()));
                        }
                    }

                    i++;
                }
            }
        }

        Collection<RecipeDetailIngredients> recipeDetailIngredientsCollection =
                recipeDetail.getRecipeDetailIngredients();
        if(recipeDetailIngredientsCollection != null && !recipeDetailIngredientsCollection.isEmpty())
        {
            this.ingredients = new ArrayList<>(recipeDetailIngredientsCollection.size());
            for(RecipeDetailIngredients recipeDetailIngredients: recipeDetailIngredientsCollection)
            {
                this.ingredients.add(new IngredientResponse(recipeDetailIngredients.getIngredient()));
            }
        }
    }

    public Long getId()
    {
        return id;
    }

    public Boolean getVegetarian()
    {
        return vegetarian;
    }

    public Boolean getVegan()
    {
        return vegan;
    }

    public Boolean getGlutenFree()
    {
        return glutenFree;
    }

    public Boolean getDairyFree()
    {
        return dairyFree;
    }

    public Boolean getVeryHealthy()
    {
        return veryHealthy;
    }

    public Boolean getCheap()
    {
        return cheap;
    }

    public Boolean getVeryPopular()
    {
        return veryPopular;
    }

    public Boolean getSustainable()
    {
        return sustainable;
    }

    public Boolean getLowFodmap()
    {
        return lowFodmap;
    }

    public Long getWeightWatcherSmartPoints()
    {
        return weightWatcherSmartPoints;
    }

    public String getGaps()
    {
        return gaps;
    }

    public Long getPreparationInMinutes()
    {
        return preparationInMinutes;
    }

    public Long getCookingMinutes()
    {
        return cookingMinutes;
    }

    public String getSourceUrl()
    {
        return sourceUrl;
    }

    public String getSpoonacularSourceUrl()
    {
        return spoonacularSourceUrl;
    }

    public Long getAggregateLikes()
    {
        return aggregateLikes;
    }

    public Double getSpoonacularScore()
    {
        return spoonacularScore;
    }

    public Double getHealthScore()
    {
        return healthScore;
    }

    public String getCreditsText()
    {
        return creditsText;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public Double getPricePerServing()
    {
        return pricePerServing;
    }

    public String getTitle()
    {
        return title;
    }

    public Long getReadyInMinutes()
    {
        return readyInMinutes;
    }

    public Long getServings()
    {
        return servings;
    }

    public String getImage()
    {
        return image;
    }

    public String getImageType()
    {
        return imageType;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public Long getOriginalId()
    {
        return originalId;
    }

    public List<String> getDishTypes()
    {
        return dishTypes;
    }

    public List<String> getCuisines()
    {
        return cuisines;
    }

    public List<List<StepResponse>> getStepOptionList()
    {
        return stepOptionList;
    }

    public List<IngredientResponse> getIngredients()
    {
        return ingredients;
    }
}
