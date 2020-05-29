package com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse;

import com.tsilva.springFood.entity.Step;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 28.05.2020.
 */

public class StepResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230027L;

    private Long id;
    private Long number;
    private String step;
    private Long lengthNumber;
    private String lengthUnit;

    public StepResponse(Step stepEntity)
    {
        if(stepEntity != null)
        {
            this.id = stepEntity.getId();
            this.number = stepEntity.getNumber();
            this.step = stepEntity.getStep();
            this.lengthNumber = stepEntity.getLengthNumber();
            this.lengthUnit = stepEntity.getLengthUnit();
        }
    }

    public Long getId()
    {
        return id;
    }

    public Long getNumber()
    {
        return number;
    }

    public String getStep()
    {
        return step;
    }

    public Long getLengthNumber()
    {
        return lengthNumber;
    }

    public String getLengthUnit()
    {
        return lengthUnit;
    }
}
