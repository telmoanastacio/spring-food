package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Measure;
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

public class MeasureDao implements IMeasureDao
{
    private static final Logger LOG = LoggerFactory.getLogger(MeasureDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public Measure findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Measure> query = currentSession.createQuery(
                "from Measure where id=:id",
                Measure.class);
        query.setParameter("id", id);

        Measure measure = null;
        try
        {
            measure = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return measure;
    }

    @Override
    public void save(Measure measure)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(measure);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
