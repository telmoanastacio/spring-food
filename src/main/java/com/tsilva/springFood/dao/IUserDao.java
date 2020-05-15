package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.User;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public interface IUserDao
{
    @Nullable
    public User findByUserName(String userName);
    
    public void save(User user);

    public void delete(User user);
}