package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.User;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public interface IUserDao
{
    public User findByUserName(String userName);
    
    public void save(User user);

    public void delete(User user);
}