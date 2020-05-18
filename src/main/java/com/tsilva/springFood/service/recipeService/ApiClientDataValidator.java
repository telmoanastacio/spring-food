package com.tsilva.springFood.service.recipeService;

import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.AnalyzedInstruction;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.ExtendedIngredient;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.RecipeInformation;
import com.tsilva.springFood.controller.apiClient.contract.recipeInformation.Step;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.Result;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Telmo Silva on 21.05.2020.
 */

public class ApiClientDataValidator implements IApiClientDataValidator
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiClientDataValidator.class);

    private Result result = null;
    private RecipeInformation recipeInformation = null;

    private ApiClientDataValidator() {}

    public ApiClientDataValidator(Result result, RecipeInformation recipeInformation) throws NullAttributeException
    {
        if(result == null)
        {
            throw new NullAttributeException("ApiClientDataValidator", "Result");
        }

        if(recipeInformation == null)
        {
            throw new NullAttributeException("ApiClientDataValidator", "RecipeInformation");
        }

        this.result = result;
        this.recipeInformation = recipeInformation;
    }

    @Override
    public boolean validateResult()
    {
        boolean isValid = IApiClientDataValidator.validateResult(result);
        if(!isValid)
        {
            LOG.debug("validateResult() Result is not valid: " + result);
        }
        return isValid;
    }

    @Override
    public boolean validateRecipeInformation()
    {
        if(recipeInformation == null)
        {
            LOG.debug("validateRecipeInformation() RecipeInformation is null");
            return false;
        }

        // validate ingredients
        List<ExtendedIngredient> extendedIngredientList = recipeInformation.extendedIngredients;
        if(extendedIngredientList != null && !extendedIngredientList.isEmpty())
        {
            for(ExtendedIngredient extendedIngredient: extendedIngredientList)
            {
                if(extendedIngredient.name == null)
                {
                    LOG.debug("validateRecipeInformation() ExtendedIngredient is not valid" + extendedIngredient);
                    return false;
                }
            }
        }

        // validate steps
        List<AnalyzedInstruction> analyzedInstructionList = recipeInformation.analyzedInstructions;
        if(analyzedInstructionList != null && !analyzedInstructionList.isEmpty())
        {
            for(AnalyzedInstruction analyzedInstruction: analyzedInstructionList)
            {
                List<Step> stepList = analyzedInstruction.steps;
                if(stepList != null && !stepList.isEmpty())
                {
                    for(Step step: stepList)
                    {
                        if(step.number == null || step.step == null)
                        {
                            LOG.debug("validateRecipeInformation() Step is not valid" + step);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public Result getResult()
    {
        return result;
    }

    public RecipeInformation getRecipeInformation()
    {
        return recipeInformation;
    }
}
