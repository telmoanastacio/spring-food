package com.tsilva.springFood.controller.apiClient.client;

import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.RecipeSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public interface SpoonacularApiClient
{
    /**
     *
     * @param spoonacularApiKey
     * @param query
     * @param offset [0 - 900]
     * @param amount [1 - 100]
     * @return
     */
    @GET("recipes/search")
    Call<RecipeSearch> getRecipeSearch(
            @Query("apiKey") String spoonacularApiKey,
            @Query("query") String query,
            @Query("offset") Long offset,
            @Query("number") Long amount);

    /**
     *
     * use getBulkRecipeInformation() if possible
     */
    @GET("recipes/{recipeId}/information")
    Call<RecipeInformation> getRecipeInformation(
            @Path("recipeId") Long recipeId,
            @Query("apiKey") String spoonacularApiKey);

    @GET("recipes/informationBulk")
    Call<List<RecipeInformation>> getBulkRecipeInformation(
            @Query("apiKey") String spoonacularApiKey,
            @Query("ids") String comaSeparatedRecipeIds);
}