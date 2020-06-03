package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.Ingredient;
import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.RecipeSaveRequest;
import com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest.Step;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

public class RecipeSaveRequestValidator implements IRecipeSaveRequestValidator
{
    private static final Logger LOG = LoggerFactory.getLogger(RecipeSaveRequestValidator.class);

    private RecipeSaveRequest recipeSaveRequest = null;

    private RecipeSaveRequestValidator() {}

    public RecipeSaveRequestValidator(RecipeSaveRequest recipeSaveRequest) throws NullAttributeException
    {
        if(recipeSaveRequest == null)
        {
            throw new NullAttributeException("RecipeSaveRequestValidator", "RecipeSaveRequest");
        }

        this.recipeSaveRequest = recipeSaveRequest;
    }

    @Override
    public boolean validateRecipeSaveRequest()
    {
        if(recipeSaveRequest == null
                || recipeSaveRequest.getRecipeBase() == null
                || recipeSaveRequest.getRecipeBase().getTitle() == null
                || recipeSaveRequest.getRecipeDetail() == null
                || recipeSaveRequest.getRecipeDetail().getIngredients() == null
                || recipeSaveRequest.getRecipeDetail().getIngredients().isEmpty()
                || recipeSaveRequest.getRecipeDetail().getStepOptionList() == null
                || recipeSaveRequest.getRecipeDetail().getStepOptionList().isEmpty())
        {
            LOG.debug("RecipeSaveRequest not valid");

            return false;
        }

        for(Ingredient ingredient: recipeSaveRequest.getRecipeDetail().getIngredients())
        {
            if(ingredient.getName() == null)
            {
                LOG.debug("RecipeSaveRequest not valid, no ingredient name.");

                return false;
            }
        }

        for(List<Step> stepList: recipeSaveRequest.getRecipeDetail().getStepOptionList())
        {
            if(stepList == null || stepList.isEmpty())
            {
                LOG.debug("RecipeSaveRequest not valid, stepList is not valid.");

                return false;
            }

            List<Long> stepNumberList = new ArrayList<>(stepList.size());
            for(Step step: stepList)
            {
                if(step == null || step.getNumber() == null || step.getStep() == null)
                {
                    LOG.debug("RecipeSaveRequest not valid, step is not valid.");

                    return false;
                }

                stepNumberList.sort(Comparator.naturalOrder());
                if(stepNumberList.contains(step.getNumber()))
                {
                    LOG.debug("RecipeSaveRequest not valid, step number is not unique.");

                    return false;
                }
                else
                {
                    stepNumberList.add(step.getNumber());
                }
            }
        }

        return true;
    }

    @Override
    public RecipeSaveRequest getRecipeSaveRequest()
    {
        return recipeSaveRequest;
    }
}
