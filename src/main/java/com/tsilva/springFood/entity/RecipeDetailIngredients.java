package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_detail_ingredients")
public class RecipeDetailIngredients implements Serializable
{
    private static final long serialVersionUID = 42865132230016L;

    @EmbeddedId
    private RecipeDetailIngredientsId recipeDetailIngredientsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeDetailId")
    private RecipeDetail recipe_detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    private RecipeDetailIngredients() {}

    public RecipeDetailIngredients(RecipeDetail recipeDetail, Ingredient ingredient)
    {
        this.recipe_detail = recipeDetail;
        this.ingredient = ingredient;
        this.recipeDetailIngredientsId = new RecipeDetailIngredientsId(recipeDetail.getId(), ingredient.getId());
    }

    public RecipeDetailIngredientsId getRecipeDetailIngredientsId()
    {
        return recipeDetailIngredientsId;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipe_detail;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public void setRecipeDetailIngredientsId(RecipeDetailIngredientsId recipeDetailIngredientsId)
    {
        this.recipeDetailIngredientsId = recipeDetailIngredientsId;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipe_detail = recipeDetail;
    }

    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
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
        RecipeDetailIngredients that = (RecipeDetailIngredients) o;
        return Objects.equals(recipe_detail, that.recipe_detail) &&
                Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipe_detail, ingredient);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailIngredients{" +
                "recipeDetailIngredientsId=" + recipeDetailIngredientsId +
                ", recipeDetail=" + recipe_detail +
                ", ingredient=" + ingredient +
                '}';
    }
}
