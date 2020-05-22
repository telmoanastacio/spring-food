package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.entity.RecipeBase;

import java.util.List;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeService
{
    public List<RecipeBase> findByRecipeName(String recipeName);
}
