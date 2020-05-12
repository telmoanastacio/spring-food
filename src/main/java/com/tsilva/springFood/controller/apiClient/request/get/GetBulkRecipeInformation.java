package com.tsilva.springFood.controller.apiClient.request.get;

import com.tsilva.springFood.controller.apiClient.ApiConfig;
import com.tsilva.springFood.controller.apiClient.ResponseCallback;
import com.tsilva.springFood.controller.apiClient.ResponseUtils;
import com.tsilva.springFood.controller.apiClient.client.SpoonacularApiClient;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class GetBulkRecipeInformation
{
    private SpoonacularApiClient spoonacularApiClient = null;

    private GetBulkRecipeInformation() {}

    public GetBulkRecipeInformation(SpoonacularApiClient spoonacularApiClient)
    {
        this.spoonacularApiClient = spoonacularApiClient;
    }

    public void execute(String comaSeparatedRecipeIds, ResponseCallback<List<RecipeInformation>> responseCallback)
    {
        spoonacularApiClient
                .getBulkRecipeInformation(ApiConfig.SPOONACULAR_API_KEY, comaSeparatedRecipeIds)
                .enqueue(new Callback<List<RecipeInformation>>()
                {
                    @Override
                    public void onResponse(
                            Call<List<RecipeInformation>> call,
                            Response<List<RecipeInformation>> response)
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
                    public void onFailure(Call<List<RecipeInformation>> call, Throwable t)
                    {
                        responseCallback.failure(t);
                    }
                });
    }

    public void execute(List<Long> recipeIds, ResponseCallback<List<RecipeInformation>> responseCallback)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("");

        for(int i = 0; i < recipeIds.size(); i++)
        {
            sb.append(recipeIds.get(i));

            if(i < recipeIds.size() - 1)
            {
                sb.append(",");
            }
        }

        spoonacularApiClient
                .getBulkRecipeInformation(ApiConfig.SPOONACULAR_API_KEY, sb.toString())
                .enqueue(new Callback<List<RecipeInformation>>()
                {
                    @Override
                    public void onResponse(
                            Call<List<RecipeInformation>> call,
                            Response<List<RecipeInformation>> response)
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
                    public void onFailure(Call<List<RecipeInformation>> call, Throwable t)
                    {
                        responseCallback.failure(t);
                    }
                });
    }
}
