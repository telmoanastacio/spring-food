package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.DishType;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IDishTypeDao extends IBaseDao<DishType>
{
    @Nullable
    public Collection<DishType> findRecipeDetailDishes(Collection<Long> dishTypeIdCollection);
}
