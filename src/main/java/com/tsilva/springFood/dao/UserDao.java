package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.User;
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
public class UserDao implements IUserDao
{
	private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Nullable
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
			currentSession.clear();
			LOG.debug("findByUserName(): ", e);
		}

		return user;
	}

	@Override
	public void save(User user)
	{
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		try
		{
			currentSession.saveOrUpdate(user);
		}
		catch (Exception e)
		{
			currentSession.clear();
			LOG.debug("save(): ", e);
		}
	}

	@Override
	public void delete(User user)
	{
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		try
		{
			currentSession.delete(user);
		}
		catch (Exception e)
		{
			currentSession.clear();
			LOG.debug("delete(): ", e);
		}
	}
}