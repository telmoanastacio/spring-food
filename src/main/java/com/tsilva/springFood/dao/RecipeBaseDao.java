package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.RecipeBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Repository
@Transactional
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
            currentSession.clear();
            LOG.debug("findByRecipeBaseName(): ", e);
        }

        return recipeBase;
    }

    @Override
    @Nullable
    public RecipeBase findBySpoonacularId(Long spoonacularId)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeBase> query = currentSession.createQuery(
                "from RecipeBase where spoonacular_id=:spoonacularId",
                RecipeBase.class);
        query.setParameter("spoonacularId", spoonacularId);

        RecipeBase recipeBase = null;
        try
        {
            recipeBase = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return recipeBase;
    }

    @Override
    @Nullable
    public List<RecipeBase> findRecipesByNameLike(String name)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(name);
        sb.append('%');

        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeBase> query = currentSession.createQuery(
                "from RecipeBase where title like:name",
                RecipeBase.class);
        query.setParameter("name", sb.toString());

        List<RecipeBase> recipeBaseList = null;
        try
        {
            recipeBaseList = query.getResultList();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findRecipesByNameLike(): ", e);
        }

        return recipeBaseList;
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
            currentSession.clear();
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
            currentSession.clear();
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
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
