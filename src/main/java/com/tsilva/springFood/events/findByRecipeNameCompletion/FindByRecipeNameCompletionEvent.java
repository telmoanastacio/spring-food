package com.tsilva.springFood.events.findByRecipeNameCompletion;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Telmo Silva on 22.05.2020.
 */

public class FindByRecipeNameCompletionEvent extends ApplicationEvent
{
    private Boolean successfulIteration = null;
    private Long offset = null;
    private Long amount = null;

    public FindByRecipeNameCompletionEvent(Object source, boolean successfulIteration, long offset, long amount)
    {
        super(source);

        this.successfulIteration = successfulIteration;
        this.offset = offset;
        this.amount = amount;
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

    @Override
    public String toString()
    {
        return "FindByRecipeNameCompletion{" +
                "successfulIteration=" + successfulIteration +
                ", offset=" + offset +
                ", amount=" + amount +
                '}';
    }
}
