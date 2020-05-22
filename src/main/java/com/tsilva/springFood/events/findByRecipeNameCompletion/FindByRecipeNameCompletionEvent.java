package com.tsilva.springFood.events.findByRecipeNameCompletion;

import com.tsilva.springFood.controller.apiServer.contract.recipeBaseSearchResponse.RecipeBaseSearchResponse;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by Telmo Silva on 22.05.2020.
 */

public class FindByRecipeNameCompletionEvent extends ApplicationEvent
{
    private String name = null;
    private Boolean successfulIteration = null;
    private Long offset = null;
    private Long amount = null;
    private DeferredResult<RecipeBaseSearchResponse> deferredResult = null;

    public FindByRecipeNameCompletionEvent(
            Object source,
            String name,
            boolean successfulIteration,
            long offset,
            long amount,
            DeferredResult<RecipeBaseSearchResponse> deferredResult)
    {
        super(source);

        this.name = name;
        this.successfulIteration = successfulIteration;
        this.offset = offset;
        this.amount = amount;
        this.deferredResult = deferredResult;
    }

    public String getName()
    {
        return name;
    }

    public Boolean getSuccessfulIteration()
    {
        return successfulIteration;
    }

    public Long getOffset()
    {
        return offset;
    }

    public Long getAmount()
    {
        return amount;
    }

    public DeferredResult<RecipeBaseSearchResponse> getDeferredResult()
    {
        return deferredResult;
    }

    @Override
    public String toString()
    {
        return "FindByRecipeNameCompletionEvent{" +
                "name='" + name + '\'' +
                ", successfulIteration=" + successfulIteration +
                ", offset=" + offset +
                ", amount=" + amount +
                '}';
    }
}
