package com.tsilva.springFood.utils;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public interface IBaseDao <T extends Serializable>
{
    @Nullable
    public T findById(Long id);

    public void save(T t);
}
