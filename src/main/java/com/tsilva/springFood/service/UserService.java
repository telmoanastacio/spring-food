package com.tsilva.springFood.service;

import com.tsilva.springFood.controller.MvcController;
import com.tsilva.springFood.dao.IRoleDao;
import com.tsilva.springFood.dao.IUserDao;
import com.tsilva.springFood.entity.Role;
import com.tsilva.springFood.entity.User;
import com.tsilva.springFood.user.CrmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Service
public class UserService implements IUserService
{
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao iUserDao;

	@Autowired
	private IRoleDao iRoleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName)
	{
		return iUserDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser)
	{
		User user = new User();
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		user.setRoles(Arrays.asList(iRoleDao.findRoleByName("USER")));

		iUserDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
		User user = iUserDao.findByUserName(userName);
		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(new Function<Role, SimpleGrantedAuthority>()
		{
			@Override
			public SimpleGrantedAuthority apply(Role role)
			{
				return new SimpleGrantedAuthority(role.getName());
			}
		}).collect(Collectors.toList());
	}
}
