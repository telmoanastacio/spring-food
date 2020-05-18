package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.DishType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Repository
public class DishTypeDao implements IDishTypeDao
{
    private static final Logger LOG = LoggerFactory.getLogger(DishTypeDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public DishType findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<DishType> query = currentSession.createQuery(
                "from DishType where id=:id",
                DishType.class);
        query.setParameter("id", id);

        DishType dishType = null;
        try
        {
            dishType = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return dishType;
    }

    @Override
    public void save(DishType dishType)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(dishType);
        }
        catch (Exception e)
        {
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
