package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Step
{
    private Long number;
    private String step;
    private Long lengthNumber;
    private String lengthUnit;

    private Step() {}

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

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public void setStep(String step)
    {
        this.step = step;
    }

    public void setLengthNumber(Long lengthNumber)
    {
        this.lengthNumber = lengthNumber;
    }

    public void setLengthUnit(String lengthUnit)
    {
        this.lengthUnit = lengthUnit;
    }
}
