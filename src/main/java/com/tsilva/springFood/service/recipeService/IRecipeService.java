package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.RecipeSaveRequest;
import com.tsilva.springFood.controller.apiServer.enums.recipeBaseSearch.SearchType;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeService
{
    public void findByRecipeName(
            String recipeName,
            SearchType searchType,
            DeferredResult<RecipeBaseSearchResponse> deferredResult);

    public boolean saveRecipe(RecipeSaveRequest recipeSaveRequest);
}
