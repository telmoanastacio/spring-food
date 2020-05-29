package com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Telmo Silva on 25.05.2020.
 */

public class RecipeBaseSearchResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230025L;

    private Integer status = null;
    private String message = null;
    private Long timeStamp = null;
    private List<RecipeBaseResponse> recipeBaseResponseList = null;

    public RecipeBaseSearchResponse(
            Integer status,
            String message,
            Long timeStamp,
            List<RecipeBaseResponse> recipeBaseResponseList)
    {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.recipeBaseResponseList = recipeBaseResponseList;
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

    public List<RecipeBaseResponse> getRecipeBaseResponseList()
    {
        return recipeBaseResponseList;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setTimeStamp(Long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public void setRecipeBaseResponseList(List<RecipeBaseResponse> recipeBaseResponseList)
    {
        this.recipeBaseResponseList = recipeBaseResponseList;
    }
}
