package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Ingredient;
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

public class IngredientDao implements IIngredientDao
{
    private static final Logger LOG = LoggerFactory.getLogger(IngredientDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public Ingredient findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Ingredient> query = currentSession.createQuery(
                "from Ingredient where id=:id",
                Ingredient.class);
        query.setParameter("id", id);

        Ingredient ingredient = null;
        try
        {
            ingredient = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return ingredient;
    }

    @Override
    public void save(Ingredient ingredient)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(ingredient);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
