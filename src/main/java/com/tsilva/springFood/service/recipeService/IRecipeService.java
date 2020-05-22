package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeService
{
    public void findByRecipeName(String recipeName, DeferredResult<RecipeBaseSearchResponse> deferredResult);
}
