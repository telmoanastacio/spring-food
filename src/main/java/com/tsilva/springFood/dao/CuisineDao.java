package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Cuisine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

public class CuisineDao implements ICuisineDao
{
    private static final Logger LOG = LoggerFactory.getLogger(CuisineDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public Cuisine findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Cuisine> query = currentSession.createQuery(
                "from Cuisine where id=:id",
                Cuisine.class);
        query.setParameter("id", id);

        Cuisine cuisine = null;
        try
        {
            cuisine = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return cuisine;
    }

    @Override
    public void save(Cuisine cuisine)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(cuisine);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
