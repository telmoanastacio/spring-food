package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Repository
public class RoleDao implements IRoleDao
{
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
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
			role = null;
		}

		return role;
	}

	@Override
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
			role = null;
		}
		
		return role;
	}
}
