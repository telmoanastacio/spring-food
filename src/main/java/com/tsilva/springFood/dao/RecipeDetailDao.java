package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeDetail;
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
public class RecipeDetailDao implements IRecipeDetailDao
{
    private static final Logger LOG = LoggerFactory.getLogger(RecipeDetailDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public RecipeDetail findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeDetail> query = currentSession.createQuery(
                "from RecipeDetail where id=:id",
                RecipeDetail.class);
        query.setParameter("id", id);

        RecipeDetail recipeDetail = null;
        try
        {
            recipeDetail = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return recipeDetail;
    }

    @Override
    public void save(RecipeDetail recipeDetail)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(recipeDetail);
        }
        catch (Exception e)
        {
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
