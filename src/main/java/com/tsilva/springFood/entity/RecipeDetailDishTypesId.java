package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Embeddable
public class RecipeDetailDishTypesId implements Serializable
{
    private static final long serialVersionUID = 42865132230009L;

    @Column(name = "recipe_detail_id")
    private Long recipeDetailId;

    @Column(name = "dish_type_id")
    private Long dishTypeId;

    private RecipeDetailDishTypesId() {}

    public RecipeDetailDishTypesId(Long recipeDetailId, Long dishTypeId)
    {
        this.recipeDetailId = recipeDetailId;
        this.dishTypeId = dishTypeId;
    }

    public Long getRecipeDetailId()
    {
        return recipeDetailId;
    }

    public Long getDishTypeId()
    {
        return dishTypeId;
    }

    public void setRecipeDetailId(Long recipeDetailId)
    {
        this.recipeDetailId = recipeDetailId;
    }

    public void setDishTypeId(Long dishTypeId)
    {
        this.dishTypeId = dishTypeId;
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
        RecipeDetailDishTypesId that = (RecipeDetailDishTypesId) o;
        return Objects.equals(recipeDetailId, that.recipeDetailId) &&
                Objects.equals(dishTypeId, that.dishTypeId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetailId, dishTypeId);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailDishTypesId{" +
                "recipeDetailId=" + recipeDetailId +
                ", dishTypeId=" + dishTypeId +
                '}';
    }
}
