package com.tsilva.springFood.controller.apiClient.request.get;

import com.tsilva.springFood.controller.apiClient.ApiConfig;
import com.tsilva.springFood.controller.apiClient.ResponseCallback;
import com.tsilva.springFood.controller.apiClient.ResponseUtils;
import com.tsilva.springFood.controller.apiClient.client.SpoonacularApiClient;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.RecipeSearch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class GetRecipeSearch
{
    private SpoonacularApiClient spoonacularApiClient = null;

    private GetRecipeSearch() {}

    public GetRecipeSearch(SpoonacularApiClient spoonacularApiClient)
    {
        this.spoonacularApiClient = spoonacularApiClient;
    }

    public void execute(String recipeQuery, ResponseCallback<RecipeSearch> responseCallback)
    {
        spoonacularApiClient.getRecipeSearch(
                ApiConfig.SPOONACULAR_API_KEY,
                recipeQuery,
                0L,
                ApiConfig.MAXIMUM_NUMBER_OF_RECIPES_PER_REQUEST)
                .enqueue(new Callback<RecipeSearch>()
                {
                    @Override
                    public void onResponse(Call<RecipeSearch> call, Response<RecipeSearch> response)
                    {
                        if(ResponseUtils.isSuccess(response))
                        {
                            responseCallback.success(response.body());
                        }
                        else
                        {
                            responseCallback.failure(ResponseUtils.buildStatusCodeThrowable(response));
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeSearch> call, Throwable t)
                    {
                        responseCallback.failure(t);
                    }
                });
    }
}
