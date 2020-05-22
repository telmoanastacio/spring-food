package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.DishType;
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
public class DishTypeDao implements IDishTypeDao
{
    private static final Logger LOG = LoggerFactory.getLogger(DishTypeDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Nullable
    public DishType findById(Long id)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<DishType> query = currentSession.createQuery(
                "from DishType where id=:id",
                DishType.class);
        query.setParameter("id", id);

        DishType dishType = null;
        try
        {
            dishType = query.getSingleResult();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findById(): ", e);
        }

        return dishType;
    }

    @Override
    @Nullable
    public Collection<DishType> findRecipeDetailDishes(Collection<Long> dishTypeIdCollection)
    {
        if(dishTypeIdCollection == null || dishTypeIdCollection.isEmpty())
        {
            return null;
        }

        Session currentSession = sessionFactory.getCurrentSession();

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("from DishType where (");

        boolean isFirst = true;
        for(Long recipeDetailId: dishTypeIdCollection)
        {
            if(isFirst)
            {
                sb.append("id=");
            }
            else
            {
                sb.append(" or id=");
            }
            sb.append(recipeDetailId);

            isFirst = false;
        }
        sb.append(")");

        Query<DishType> query = currentSession.createQuery(sb.toString(), DishType.class);

        List<DishType> dishTypeList = null;
        try
        {
            dishTypeList = query.getResultList();
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findRecipeDetailDishes(): ", e);
        }

        return dishTypeList;
    }

    @Override
    public void save(DishType dishType)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        try
        {
            currentSession.saveOrUpdate(dishType);
        }
        catch (Exception e)
        {
            currentSession.clear();
            LOG.debug("save(): ", e);
        }
    }
}
