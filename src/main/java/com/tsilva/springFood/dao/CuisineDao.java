package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Cuisine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Repository
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
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return cuisine;
    }

    @Override
    @Nullable
    public Collection<Cuisine> findRecipeDetailCuisines(Collection<Long> cuisineIdCollection)
    {
        if(cuisineIdCollection == null || cuisineIdCollection.isEmpty())
        {
            return null;
        }

        Session currentSession = sessionFactory.getCurrentSession();

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("from Cuisine where (");

        boolean isFirst = true;
        for(Long cuisineId: cuisineIdCollection)
        {
            if(isFirst)
            {
                sb.append("id=");
            }
            else
            {
                sb.append(" or id=");
            }
            sb.append(cuisineId);

            isFirst = false;
        }
        sb.append(")");

        Query<Cuisine> query = currentSession.createQuery(sb.toString(), Cuisine.class);

        List<Cuisine> cuisineList = null;
        try
        {
            cuisineList = query.getResultList();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findRecipeDetailCuisines(): ", e);
        }

        return cuisineList;
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
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
