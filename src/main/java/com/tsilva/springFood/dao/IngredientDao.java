package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.Ingredient;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Telmo Silva on 18.05.2020.
 */

@Repository
@Transactional
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
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return ingredient;
    }

    @Override
    @Nullable
    public Collection<Ingredient> findRecipeDetailIngredients(Collection<Long> ingredientIdCollection)
    {
        if(ingredientIdCollection == null || ingredientIdCollection.isEmpty())
        {
            return null;
        }

        Session currentSession = sessionFactory.getCurrentSession();

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("from Ingredient where (");

        boolean isFirst = true;
        for(Long ingredientId: ingredientIdCollection)
        {
            if(isFirst)
            {
                sb.append("id=");
            }
            else
            {
                sb.append(" or id=");
            }
            sb.append(ingredientId);

            isFirst = false;
        }
        sb.append(")");

        Query<Ingredient> query = currentSession.createQuery(sb.toString(), Ingredient.class);

        List<Ingredient> ingredientList = null;
        try
        {
            ingredientList = query.getResultList();

            for(Ingredient ingredient: ingredientList)
            {
                Hibernate.initialize(ingredient.getIngredientMetas());
                Hibernate.initialize(ingredient.getIngredientMetaInformations());
            }
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findRecipeDetailIngredients(): ", e);
        }

        return ingredientList;
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
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
