package com.tsilva.springFood.dao;

import com.tsilva.springFood.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IDishTypeDao iDishTypeDao;

    @Autowired
    private ICuisineDao iCuisineDao;

    @Autowired
    private IStepDao iStepDao;

    @Autowired
    private IIngredientDao iIngredientDao;

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
    public RecipeDetail findByRecipeBaseIdAllLoaded(Long recipeBaseId)
    {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecipeDetail> query = currentSession.createQuery(
                "from RecipeDetail where recipe_base_id=:recipeBaseId",
                RecipeDetail.class);
        query.setParameter("recipeBaseId", recipeBaseId);

        RecipeDetail recipeDetail = null;
        try
        {
            recipeDetail = query.getSingleResult();

            if(recipeDetail != null && recipeDetail.getRecipeDetailDishTypes() != null)
            {
                // populate DishType list
                List<Long> dishTypeIdList = new ArrayList<>(recipeDetail.getRecipeDetailDishTypes().size());
                for(RecipeDetailDishTypes recipeDetailDishTypes: recipeDetail.getRecipeDetailDishTypes())
                {
                    if(recipeDetailDishTypes != null
                            && recipeDetailDishTypes.getRecipeDetailDishTypesId() != null
                            && recipeDetailDishTypes.getRecipeDetailDishTypesId().getDishTypeId() != null)
                    {
                        dishTypeIdList.add(recipeDetailDishTypes.getRecipeDetailDishTypesId().getDishTypeId());
                    }
                }
                iDishTypeDao.findRecipeDetailDishes(dishTypeIdList);

                // populate Cuisine list
                List<Long> cuisineIdList = new ArrayList<>(recipeDetail.getRecipeDetailCuisines().size());
                for(RecipeDetailCuisines recipeDetailCuisines: recipeDetail.getRecipeDetailCuisines())
                {
                    if(recipeDetailCuisines != null
                            && recipeDetailCuisines.getRecipeDetailCuisinesId() != null
                            && recipeDetailCuisines.getRecipeDetailCuisinesId().getCuisineId() != null)
                    {
                        cuisineIdList.add(recipeDetailCuisines.getRecipeDetailCuisinesId().getCuisineId());
                    }
                }
                iCuisineDao.findRecipeDetailCuisines(cuisineIdList);

                // populate Step list
                List<Long> stepIdList = new ArrayList<>(recipeDetail.getRecipeDetailSteps().size());
                for(RecipeDetailSteps recipeDetailSteps: recipeDetail.getRecipeDetailSteps())
                {
                    if(recipeDetailSteps != null
                            && recipeDetailSteps.getRecipeDetailStepsId() != null
                            && recipeDetailSteps.getRecipeDetailStepsId().getStepId() != null)
                    {
                        stepIdList.add(recipeDetailSteps.getRecipeDetailStepsId().getStepId());
                    }
                }
                iStepDao.findRecipeDetailSteps(stepIdList);

                // populate Ingredient list
                List<Long> ingredientIdList = new ArrayList<>(recipeDetail.getRecipeDetailIngredients().size());
                for(RecipeDetailIngredients recipeDetailIngredients: recipeDetail.getRecipeDetailIngredients())
                {
                    if(recipeDetailIngredients != null
                            && recipeDetailIngredients.getRecipeDetailIngredientsId() != null
                            && recipeDetailIngredients.getRecipeDetailIngredientsId().getIngredientId() != null)
                    {
                        ingredientIdList.add(recipeDetailIngredients.getRecipeDetailIngredientsId().getIngredientId());
                    }
                }
                iIngredientDao.findRecipeDetailIngredients(ingredientIdList);
            }
        }
        catch(Exception e)
        {
            currentSession.clear();
            LOG.debug("findByRecipeBaseIdAllLoaded(): ", e);
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
