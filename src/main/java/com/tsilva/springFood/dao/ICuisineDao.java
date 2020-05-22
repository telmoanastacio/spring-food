package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Cuisine;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface ICuisineDao extends IBaseDao<Cuisine>
{
    @Nullable
    public Collection<Cuisine> findRecipeDetailCuisines(Collection<Long> cuisineIdCollection);
}
