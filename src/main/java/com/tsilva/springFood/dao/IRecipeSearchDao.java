package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeSearch;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IRecipeSearchDao extends IBaseDao<RecipeSearch>
{
    @Nullable
    public RecipeSearch findByRecipeSearchName(String queryStr);
}
