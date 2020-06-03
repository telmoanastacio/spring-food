package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeBase
{
    private String title;
    private Long readyInMinutes;
    private Long servings;
    private String image;

    private RecipeBase() {}

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
}
