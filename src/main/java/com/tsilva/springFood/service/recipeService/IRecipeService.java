package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.entity.RecipeBase;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeService
{
    public RecipeBase findByRecipeName(String recipeName);
}
