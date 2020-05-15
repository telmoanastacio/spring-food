package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Repository
public class RoleDao implements IRoleDao
{
	private static final Logger LOG = LoggerFactory.getLogger(RoleDao.class);

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Nullable
	public Role findRoleById(Long roleId)
	{
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> query = currentSession.createQuery("from Role where id=:roleId", Role.class);
		query.setParameter("roleId", roleId);

		Role role = null;
		try
		{
			role = query.getSingleResult();
		}
		catch(Exception e)
		{
			LOG.debug("findRoleById(): ", e);
		}

		return role;
	}

	@Override
	@Nullable
	public Role findRoleByName(String roleName)
	{
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		query.setParameter("roleName", roleName);
		
		Role role = null;
		try
		{
			role = query.getSingleResult();
		}
		catch(Exception e)
		{
			LOG.debug("findRoleByName(): ", e);
		}
		
		return role;
	}
}
