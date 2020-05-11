package com.tsilva.springFood.service;


import com.tsilva.springFood.entity.User;
import com.tsilva.springFood.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public interface IUserService extends UserDetailsService
{
    User findByUserName(String userName);

    void save(CrmUser crmUser);

    void delete(User user);
}
