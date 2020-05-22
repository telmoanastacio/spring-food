package com.tsilva.springFood.controller.apiServer;

import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse.RecipeDetailResponse;
import com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse.RecipeDetailSearchResponse;
import com.tsilva.springFood.dao.IRecipeBaseDao;
import com.tsilva.springFood.dao.IRecipeDetailDao;
import com.tsilva.springFood.entity.RecipeBase;
import com.tsilva.springFood.entity.RecipeDetail;
import com.tsilva.springFood.events.findByRecipeNameCompletion.FindByRecipeNameCompletionEvent;
import com.tsilva.springFood.service.recipeService.IRecipeService;
import com.tsilva.springFood.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@RestController
@RequestMapping("/spring-food-api")
public class ApiControllerServer implements ApplicationListener<FindByRecipeNameCompletionEvent>
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiControllerServer.class);

    @Autowired
    private IRecipeService iRecipeService;

    @Autowired
    private IRecipeBaseDao iRecipeBaseDao;

    @Autowired
    private IRecipeDetailDao iRecipeDetailDao;

    @RequestMapping(value = "/recipes/{recipeName}", method = RequestMethod.GET)
    public DeferredResult<RecipeBaseSearchResponse> recipeMapping(@PathVariable(name = "recipeName") String recipeName)
    {
        DeferredResult<RecipeBaseSearchResponse> deferredResult = new DeferredResult<>(300000L);

        iRecipeService.findByRecipeName(recipeName, deferredResult);

        return deferredResult;
    }

    @RequestMapping(value = "/recipeDetail/{recipeBaseId}", method = RequestMethod.GET)
    public RecipeDetailSearchResponse recipeMapping(@PathVariable(name = "recipeBaseId") Long recipeBaseId)
    {
        Date now = TimeUtils.now();

        RecipeDetail recipeDetail = iRecipeDetailDao.findByRecipeBaseIdAllLoaded(recipeBaseId);

        RecipeDetailSearchResponse recipeDetailSearchResponse = null;
        if(recipeDetail == null)
        {
            // send error response, no data found
            recipeDetailSearchResponse = new RecipeDetailSearchResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Recipe detail not found for recipeBaseId: " + recipeBaseId,
                    now.getTime(),
                    null);

            LOG.debug("Recipe detail not found for recipeBaseId: " + recipeBaseId);
        }
        else
        {
            recipeDetailSearchResponse = new RecipeDetailSearchResponse(
                    HttpStatus.OK.value(),
                    "Result found for recipeBaseId: " + recipeBaseId,
                    now.getTime(),
                    new RecipeDetailResponse(recipeDetail));
        }

        return recipeDetailSearchResponse;
    }

    @Override
    public void onApplicationEvent(FindByRecipeNameCompletionEvent event)
    {
        Date now = TimeUtils.now();

        String recipeName = event.getName();
        DeferredResult<RecipeBaseSearchResponse> deferredResult = event.getDeferredResult();

        List<RecipeBase> recipeBaseList = iRecipeBaseDao.findRecipesByNameLike(recipeName);
        RecipeBaseSearchResponse recipeBaseSearchResponse = null;
        if(recipeBaseList == null || recipeBaseList.isEmpty())
        {
            // send error response, no data found
            recipeBaseSearchResponse = new RecipeBaseSearchResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "No recipe was found with the name: " + recipeName,
                    now.getTime(),
                    null);

            LOG.debug("No recipe was found with the name: " + recipeName);
        }
        else
        {
            List<RecipeBaseResponse> recipeBaseResponseList = new ArrayList<>(recipeBaseList.size());
            for(RecipeBase recipeBase: recipeBaseList)
            {
                if(recipeBase != null)
                {
                    RecipeBaseResponse recipeBaseResponse = new RecipeBaseResponse(recipeBase);
                    if(recipeBaseResponse.getId() != null)
                    {
                        recipeBaseResponseList.add(recipeBaseResponse);
                    }
                }
            }

            recipeBaseSearchResponse = new RecipeBaseSearchResponse(
                    HttpStatus.OK.value(),
                    "Result found for: " + recipeName,
                    now.getTime(),
                    recipeBaseResponseList);
        }

        deferredResult.setResult(recipeBaseSearchResponse);
    }
}