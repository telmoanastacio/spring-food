package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Step;
import com.tsilva.springFood.utils.IBaseDao;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IStepDao extends IBaseDao<Step>
{
    @Nullable
    public Collection<Step> findRecipeDetailSteps(Collection<Long> stepIdCollection);
}
