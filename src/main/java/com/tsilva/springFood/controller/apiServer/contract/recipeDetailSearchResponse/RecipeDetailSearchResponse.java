package com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 28.05.2020.
 */

public class RecipeDetailSearchResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230030L;

    private Integer status = null;
    private String message = null;
    private Long timeStamp = null;
    private RecipeDetailResponse recipeDetail = null;

    public RecipeDetailSearchResponse(Integer status, String message, Long timeStamp, RecipeDetailResponse recipeDetail)
    {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.recipeDetail = recipeDetail;
    }

    public Integer getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    public Long getTimeStamp()
    {
        return timeStamp;
    }

    public RecipeDetailResponse getRecipeDetail()
    {
        return recipeDetail;
    }
}
