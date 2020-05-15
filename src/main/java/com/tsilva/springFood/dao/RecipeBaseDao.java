package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeBase;
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

public class RecipeBaseDao implements IRecipeBaseDao
{
    private static final Logger LOG = LoggerFactory.getLogger(RecipeBaseDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public RecipeBase findByRecipeBaseName(String name)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeBase> query = currentSession.createQuery(
                "from RecipeBase where title=:name",
                RecipeBase.class);
        query.setParameter("name", name);

        RecipeBase recipeBase = null;
        try
        {
            recipeBase = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findByRecipeBaseName(): ", e);
        }

        return recipeBase;
    }

    @Override
    public void delete(RecipeBase recipeBase)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.delete(recipeBase);
        }
        catch (Exception e)
        {
            LOG.debug("delete(): ", e);
        }
    }

    @Override
    @Nullable
    public RecipeBase findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeBase> query = currentSession.createQuery(
                "from RecipeBase where id=:id",
                RecipeBase.class);
        query.setParameter("id", id);

        RecipeBase recipeBase = null;
        try
        {
            recipeBase = query.getSingleResult();
        }
        catch(Exception e)
        {
            LOG.debug("findById(): ", e);
        }

        return recipeBase;
    }

    @Override
    public void save(RecipeBase recipeBase)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(recipeBase);
        }
        catch (Exception e)
        {
            LOG.debug("save(): ", e);
        }
    }
}
