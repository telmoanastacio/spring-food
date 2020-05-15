package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Step;
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
public class StepDao implements IStepDao
{
    private static final Logger LOG = LoggerFactory.getLogger(StepDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public Step findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Step> query = currentSession.createQuery("from Step where id=:id", Step.class);
        query.setParameter("id", id);

        Step step = null;
        try
        {
            step = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return step;
    }

    @Override
    public void save(Step step)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(step);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
