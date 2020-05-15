package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.IngredientMetas;
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

public class IngredientMetasDao implements IIngredientMetasDao
{
    private static final Logger LOG = LoggerFactory.getLogger(IngredientMetasDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public IngredientMetas findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<IngredientMetas> query = currentSession.createQuery(
                "from IngredientMetas where id=:id",
                IngredientMetas.class);
        query.setParameter("id", id);

        IngredientMetas ingredientMetas = null;
        try
        {
            ingredientMetas = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return ingredientMetas;
    }

    @Override
    public void save(IngredientMetas ingredientMetas)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(ingredientMetas);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
