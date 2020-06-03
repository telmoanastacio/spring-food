package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.RecipeSaveRequest;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

public interface IRecipeSaveRequestValidator
{
    /**
     * @return {@code true} if data is valid
     */
    public boolean validateRecipeSaveRequest();

    public RecipeSaveRequest getRecipeSaveRequest();
}
