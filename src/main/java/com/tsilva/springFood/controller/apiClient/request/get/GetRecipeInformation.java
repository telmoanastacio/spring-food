package com.tsilva.springFood.controller.apiClient.request.get;

import com.tsilva.springFood.controller.apiClient.ApiConfig;
import com.tsilva.springFood.controller.apiClient.ResponseCallback;
import com.tsilva.springFood.controller.apiClient.ResponseUtils;
import com.tsilva.springFood.controller.apiClient.client.SpoonacularApiClient;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class GetRecipeInformation
{
    private SpoonacularApiClient spoonacularApiClient = null;

    private GetRecipeInformation() {}

    public GetRecipeInformation(SpoonacularApiClient spoonacularApiClient)
    {
        this.spoonacularApiClient = spoonacularApiClient;
    }

    public void execute(Long recipeId, ResponseCallback<RecipeInformation> responseCallback)
    {
        spoonacularApiClient
                .getRecipeInformation(ApiConfig.SPOONACULAR_API_KEY, recipeId)
                .enqueue(new Callback<RecipeInformation>()
                {
                    @Override
                    public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> response)
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
                    public void onFailure(Call<RecipeInformation> call, Throwable t)
                    {
                        responseCallback.failure(t);
                    }
                });
    }
}
