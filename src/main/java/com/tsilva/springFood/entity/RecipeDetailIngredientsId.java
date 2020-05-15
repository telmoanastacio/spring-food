package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Embeddable
public class RecipeDetailIngredientsId implements Serializable
{
    private static final long serialVersionUID = 42865132230015L;

    @Column(name = "recipe_detail_id")
    private Long recipeDetailId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    private RecipeDetailIngredientsId() {}

    public RecipeDetailIngredientsId(Long recipeDetailId, Long ingredientId)
    {
        this.recipeDetailId = recipeDetailId;
        this.ingredientId = ingredientId;
    }

    public Long getRecipeDetailId()
    {
        return recipeDetailId;
    }

    public Long getIngredientId()
    {
        return ingredientId;
    }

    public void setRecipeDetailId(Long recipeDetailId)
    {
        this.recipeDetailId = recipeDetailId;
    }

    public void setIngredientId(Long ingredientId)
    {
        this.ingredientId = ingredientId;
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
        RecipeDetailIngredientsId that = (RecipeDetailIngredientsId) o;
        return Objects.equals(recipeDetailId, that.recipeDetailId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetailId, ingredientId);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailIngredientsId{" +
                "recipeDetailId=" + recipeDetailId +
                ", ingredientId=" + ingredientId +
                '}';
    }
}
