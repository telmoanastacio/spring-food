package com.tsilva.springFood.events.findByRecipeNameCompletion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Created by Telmo Silva on 22.05.2020.
 */

@Component
public class FindByRecipeNameCompletionEventPublisher
{
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(FindByRecipeNameCompletionEvent findByRecipeNameCompletionEvent)
    {
        applicationEventPublisher.publishEvent(findByRecipeNameCompletionEvent);
    }
}
