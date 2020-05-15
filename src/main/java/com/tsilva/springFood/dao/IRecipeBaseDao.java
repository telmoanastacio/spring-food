package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeBase;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeBaseDao extends IBaseDao<RecipeBase>
{
    @Nullable
    public RecipeBase findByRecipeBaseName(String name);

    public void delete(RecipeBase recipeBase);
}
