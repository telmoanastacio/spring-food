package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeSearch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Repository
@Transactional
public class RecipeSearchDao implements IRecipeSearchDao
{
    private static final Logger LOG = LoggerFactory.getLogger(RecipeSearchDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public RecipeSearch findByRecipeSearchName(String queryStr)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeSearch> query = currentSession.createQuery(
                "from RecipeSearch where searchQuery=:queryStr",
                RecipeSearch.class);
        query.setParameter("queryStr", queryStr);

        RecipeSearch recipeSearch = null;
        try
        {
            recipeSearch = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findByRecipeSearchName(): ", e);
        }

        return recipeSearch;
    }

    @Override
    @Nullable
    public RecipeSearch findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeSearch> query = currentSession.createQuery(
                "from RecipeSearch where id=:id",
                RecipeSearch.class);
        query.setParameter("id", id);

        RecipeSearch recipeSearch = null;
        try
        {
            recipeSearch = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return recipeSearch;
    }

    @Override
    public void save(RecipeSearch recipeSearch)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(recipeSearch);
        }
        catch (Exception e)
        {
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
