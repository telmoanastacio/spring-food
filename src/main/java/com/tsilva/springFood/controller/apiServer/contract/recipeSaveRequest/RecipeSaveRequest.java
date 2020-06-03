package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Telmo Silva on 03.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeSaveRequest
{
    private RecipeBase recipeBase;
    private RecipeDetail recipeDetail;

    private RecipeSaveRequest() {}

    public RecipeBase getRecipeBase()
    {
        return recipeBase;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipeDetail;
    }

    public void setRecipeBase(RecipeBase recipeBase)
    {
        this.recipeBase = recipeBase;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipeDetail = recipeDetail;
    }
}
