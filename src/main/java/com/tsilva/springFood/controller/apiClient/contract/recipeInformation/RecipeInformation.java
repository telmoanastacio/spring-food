package com.tsilva.springFood.controller.apiClient.contract.recipeInformation;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class RecipeInformation
{
    public Boolean vegetarian;
    public Boolean vegan;
    public Boolean glutenFree;
    public Boolean dairyFree;
    public Boolean veryHealthy;
    public Boolean cheap;
    public Boolean veryPopular;
    public Boolean sustainable;
    public Long weightWatcherSmartPoints;
    public String gaps;
    public Boolean lowFodmap;
    public Long preparationMinutes;
    public Long cookingMinutes;
    public Long aggregateLikes;
    public Double spoonacularScore;
    public Double healthScore;
    public String creditsText;
    public String license;
    public String sourceName;
    public Double pricePerServing;
    public List<ExtendedIngredient> extendedIngredients;
    public Long id;
    public String title;
    public Long readyInMinutes;
    public Long servings;
    public String sourceUrl;
    public String image;
    public String imageType;
    public String summary;
    public List<String> cuisines;
    public List<String> dishTypes;
    public List<String> diets;
    public List<String> occasions;
    public WinePairing winePairing;
    public String instructions;
    public List<AnalyzedInstruction> analyzedInstructions;
    public Long originalId;
    public String spoonacularSourceUrl;
}
