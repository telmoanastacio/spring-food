package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_detail_dish_types")
public class RecipeDetailDishTypes implements Serializable
{
    private static final long serialVersionUID = 42865132230010L;

    @EmbeddedId
    private RecipeDetailDishTypesId recipeDetailDishTypesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeDetailId")
    private RecipeDetail recipe_detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishTypeId")
    private DishType dish_type;

    private RecipeDetailDishTypes() {}

    public RecipeDetailDishTypes(RecipeDetail recipeDetail, DishType dishType)
    {
        this.recipe_detail = recipeDetail;
        this.dish_type = dishType;
        this.recipeDetailDishTypesId = new RecipeDetailDishTypesId(recipeDetail.getId(), dishType.getId());
    }

    public RecipeDetailDishTypesId getRecipeDetailDishTypesId()
    {
        return recipeDetailDishTypesId;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipe_detail;
    }

    public DishType getDishType()
    {
        return dish_type;
    }

    public void setRecipeDetailDishTypesId(RecipeDetailDishTypesId recipeDetailDishTypesId)
    {
        this.recipeDetailDishTypesId = recipeDetailDishTypesId;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipe_detail = recipeDetail;
    }

    public void setDishType(DishType dishType)
    {
        this.dish_type = dishType;
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
        RecipeDetailDishTypes that = (RecipeDetailDishTypes) o;
        return Objects.equals(recipe_detail, that.recipe_detail) &&
                Objects.equals(dish_type, that.dish_type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipe_detail, dish_type);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailDishTypes{" +
                "recipeDetailDishTypesId=" + recipeDetailDishTypesId +
                ", recipeDetail=" + recipe_detail +
                ", dishType=" + dish_type +
                '}';
    }
}
