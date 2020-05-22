package com.tsilva.springFood.controller.apiClient;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class ApiConfig
{
    public static final String SPOONACULAR_API_KEY = "ff757ffedaa5461991013c18cd42ebdc";
    public static final String SPOONACULAR_API_ENDPOINT = "https://api.spoonacular.com/";
    public static final long MAXIMUM_NUMBER_OF_RECIPES_PER_REQUEST = 100L;
    public static final int WAIT_AFTER_SUCCESSFUL_SEARCH_SECONDS = 30 * 24 * 3600;
}