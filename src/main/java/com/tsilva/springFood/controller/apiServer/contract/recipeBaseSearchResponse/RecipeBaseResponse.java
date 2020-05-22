package com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse;

import com.tsilva.springFood.entity.RecipeBase;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 25.05.2020.
 */

public class  RecipeBaseResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230024L;

    private Long id = null;
    private Long spoonacularId = null;
    private String title = null;
    private Long readyInMinutes = null;
    private Long servings = null;
    private String image = null;
    private Long updateTimeStamp = null;

    public RecipeBaseResponse(@NonNull RecipeBase recipeBase)
    {
        if(recipeBase.getId() != null && recipeBase.getSpoonacularId() != null && recipeBase.getTitle() != null)
        {
            this.id = recipeBase.getId();
            this.spoonacularId = recipeBase.getSpoonacularId();
            this.title = recipeBase.getTitle();
            this.readyInMinutes = recipeBase.getReadyInMinutes();
            this.servings = recipeBase.getServings();
            this.image = recipeBase.getImage();
            this.updateTimeStamp = recipeBase.getUpdateTimeStamp();
        }
    }

    public Long getId()
    {
        return id;
    }

    public Long getSpoonacularId()
    {
        return spoonacularId;
    }

    public String getTitle()
    {
        return title;
    }

    public Long getReadyInMinutes()
    {
        return readyInMinutes;
    }

    public Long getServings()
    {
        return servings;
    }

    public String getImage()
    {
        return image;
    }

    public Long getUpdateTimeStamp()
    {
        return updateTimeStamp;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setSpoonacularId(Long spoonacularId)
    {
        this.spoonacularId = spoonacularId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setReadyInMinutes(Long readyInMinutes)
    {
        this.readyInMinutes = readyInMinutes;
    }

    public void setServings(Long servings)
    {
        this.servings = servings;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setUpdateTimeStamp(Long updateTimeStamp)
    {
        this.updateTimeStamp = updateTimeStamp;
    }
}
