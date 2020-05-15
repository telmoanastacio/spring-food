package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_detail_cuisines")
public class RecipeDetailCuisines implements Serializable
{
    private static final long serialVersionUID = 42865132230013L;

    @EmbeddedId
    private RecipeDetailCuisinesId recipeDetailCuisinesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeDetailId")
    private RecipeDetail recipeDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cuisineId")
    private Cuisine cuisine;

    private RecipeDetailCuisines() {}

    public RecipeDetailCuisines(RecipeDetail recipeDetail, Cuisine cuisine)
    {
        this.recipeDetail = recipeDetail;
        this.cuisine = cuisine;
        this.recipeDetailCuisinesId = new RecipeDetailCuisinesId(recipeDetail.getId(), cuisine.getId());
    }

    public RecipeDetailCuisinesId getRecipeDetailCuisinesId()
    {
        return recipeDetailCuisinesId;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipeDetail;
    }

    public Cuisine getCuisine()
    {
        return cuisine;
    }

    public void setRecipeDetailCuisinesId(RecipeDetailCuisinesId recipeDetailCuisinesId)
    {
        this.recipeDetailCuisinesId = recipeDetailCuisinesId;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipeDetail = recipeDetail;
    }

    public void setCuisine(Cuisine cuisine)
    {
        this.cuisine = cuisine;
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
        RecipeDetailCuisines that = (RecipeDetailCuisines) o;
        return Objects.equals(recipeDetail, that.recipeDetail) &&
                Objects.equals(cuisine, that.cuisine);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetail, cuisine);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailCuisines{" +
                "recipeDetailCuisinesId=" + recipeDetailCuisinesId +
                ", recipeDetail=" + recipeDetail +
                ", cuisine=" + cuisine +
                '}';
    }
}