package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.IngredientMetaInformations;
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
public class IngredientMetaInformationsDao implements IIngredientMetaInformationsDao
{
    private static final Logger LOG = LoggerFactory.getLogger(IngredientMetaInformationsDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public IngredientMetaInformations findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<IngredientMetaInformations> query = currentSession.createQuery(
                "from IngredientMetaInformations where id=:id",
                IngredientMetaInformations.class);
        query.setParameter("id", id);

        IngredientMetaInformations ingredientMetaInformations = null;
        try
        {
            ingredientMetaInformations = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return ingredientMetaInformations;
    }

    @Override
    public void save(IngredientMetaInformations ingredientMetaInformations)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(ingredientMetaInformations);
        }
        catch (Exception e)
        {
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
