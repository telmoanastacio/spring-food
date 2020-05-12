package com.tsilva.springFood.controller.apiClient.contract.recipeSearch;

import java.util.List;

/**
 * Created by Telmo Silva on 12.05.2020.
 */

public class RecipeSearch
{
    public List<Result> results;
    public String baseUri;
    public Long offset;
    public Long number;
    public Long totalResults;
    public Long processingTimeMs;
    public Long expires;
}