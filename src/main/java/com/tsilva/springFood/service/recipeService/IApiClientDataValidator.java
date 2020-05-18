package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.Result;

/**
 * Created by Telmo Silva on 21.05.2020.
 */

public interface IApiClientDataValidator
{
    /**
     * @return {@code true} if data is valid
     */
    public static boolean validateResult(Result result)
    {
        if(result == null || result.id == null || result.title == null)
        {
            return false;
        }

        return true;
    }

    /**
     * @return {@code true} if data is valid
     */
    public boolean validateResult();

    /**
     * @return {@code true} if data is valid
     */
    public boolean validateRecipeInformation();

    public Result getResult();

    public RecipeInformation getRecipeInformation();
}
