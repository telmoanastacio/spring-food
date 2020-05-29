package com.tsilva.springFood.controller.apiClient;

import com.tsilva.springFood.controller.apiClient.client.SpoonacularApiClient;
import com.tsilva.springFood.controller.apiClient.request.get.GetBulkRecipeInformation;
import com.tsilva.springFood.controller.apiClient.request.get.GetRecipeInformation;
import com.tsilva.springFood.controller.apiClient.request.get.GetRecipeSearch;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

@Controller
public class ApiControllerClient
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiControllerClient.class);

    @Bean
    public SpoonacularApiClient provideSpoonacularApiClient()
    {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(180, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.SPOONACULAR_API_ENDPOINT)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SpoonacularApiClient.class);
    }

    @Bean
    public GetRecipeSearch provideGetRecipeSearch(SpoonacularApiClient spoonacularApiClient)
    {
        return new GetRecipeSearch(spoonacularApiClient);
    }

    @Bean
    public GetRecipeInformation provideGetRecipeInformation(SpoonacularApiClient spoonacularApiClient)
    {
        return new GetRecipeInformation(spoonacularApiClient);
    }

    @Bean
    public GetBulkRecipeInformation provideGetBulkRecipeInformation(SpoonacularApiClient spoonacularApiClient)
    {
        return new GetBulkRecipeInformation(spoonacularApiClient);
    }
}