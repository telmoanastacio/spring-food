package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeDetail;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeDetailDao extends IBaseDao<RecipeDetail>
{
    @Nullable
    public RecipeDetail findByRecipeBaseIdAllLoaded(Long recipeBaseId);
}
