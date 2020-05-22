package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Ingredient;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IIngredientDao extends IBaseDao<Ingredient>
{
    @Nullable
    public Collection<Ingredient> findRecipeDetailIngredients(Collection<Long> ingredientIdCollection);
}
