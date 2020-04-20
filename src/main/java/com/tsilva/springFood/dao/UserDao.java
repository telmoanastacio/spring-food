package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Repository
public class UserDao implements IUserDao
{
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName)
	{
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// retrieve/read from database using username
		Query<User> query = currentSession.createQuery("from User where userName=:userName", User.class);
		query.setParameter("userName", userName);

		User user = null;
		try
		{
			user = query.getSingleResult();
		}
		catch(Exception e)
		{
			user = null;
		}

		return user;
	}

	@Override
	public void save(User theUser)
	{
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		try
		{
			currentSession.saveOrUpdate(theUser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}