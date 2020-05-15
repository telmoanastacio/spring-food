package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Embeddable
public class RecipeDetailCuisinesId implements Serializable
{
    private static final long serialVersionUID = 42865132230012L;

    @Column(name = "recipe_detail_id")
    private Long recipeDetailId;

    @Column(name = "cuisine_id")
    private Long cuisineId;

    private RecipeDetailCuisinesId() {}

    public RecipeDetailCuisinesId(Long recipeDetailId, Long cuisineId)
    {
        this.recipeDetailId = recipeDetailId;
        this.cuisineId = cuisineId;
    }

    public Long getRecipeDetailId()
    {
        return recipeDetailId;
    }

    public Long getCuisineId()
    {
        return cuisineId;
    }

    public void setRecipeDetailId(Long recipeDetailId)
    {
        this.recipeDetailId = recipeDetailId;
    }

    public void setCuisineId(Long cuisineId)
    {
        this.cuisineId = cuisineId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RecipeDetailCuisinesId that = (RecipeDetailCuisinesId) o;
        return Objects.equals(recipeDetailId, that.recipeDetailId) &&
                Objects.equals(cuisineId, that.cuisineId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetailId, cuisineId);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailCuisinesId{" +
                "recipeDetailId=" + recipeDetailId +
                ", cuisineId=" + cuisineId +
                '}';
    }
}
