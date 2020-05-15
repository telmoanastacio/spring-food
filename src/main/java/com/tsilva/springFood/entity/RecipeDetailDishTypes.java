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
    private RecipeDetail recipeDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishTypeId")
    private DishType dishType;

    private RecipeDetailDishTypes() {}

    public RecipeDetailDishTypes(RecipeDetail recipeDetail, DishType dishType)
    {
        this.recipeDetail = recipeDetail;
        this.dishType = dishType;
        this.recipeDetailDishTypesId = new RecipeDetailDishTypesId(recipeDetail.getId(), dishType.getId());
    }

    public RecipeDetailDishTypesId getRecipeDetailDishTypesId()
    {
        return recipeDetailDishTypesId;
    }

    public RecipeDetail getRecipeDetail()
    {
        return recipeDetail;
    }

    public DishType getDishType()
    {
        return dishType;
    }

    public void setRecipeDetailDishTypesId(RecipeDetailDishTypesId recipeDetailDishTypesId)
    {
        this.recipeDetailDishTypesId = recipeDetailDishTypesId;
    }

    public void setRecipeDetail(RecipeDetail recipeDetail)
    {
        this.recipeDetail = recipeDetail;
    }

    public void setDishType(DishType dishType)
    {
        this.dishType = dishType;
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
        return Objects.equals(recipeDetail, that.recipeDetail) &&
                Objects.equals(dishType, that.dishType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipeDetail, dishType);
    }

    @Override
    public String toString()
    {
        return "RecipeDetailDishTypes{" +
                "recipeDetailDishTypesId=" + recipeDetailDishTypesId +
                ", recipeDetail=" + recipeDetail +
                ", dishType=" + dishType +
                '}';
    }
}