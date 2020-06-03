package com.tsilva.springFood.controller.apiServer.contract.recipeSaveRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure
{
    private String unitShort;
    private String unitLong;
    private String impUnitShort;
    private String impUnitLong;

    public Measure() {}

    public String getUnitShort()
    {
        return unitShort;
    }

    public String getUnitLong()
    {
        return unitLong;
    }

    public String getImpUnitShort()
    {
        return impUnitShort;
    }

    public String getImpUnitLong()
    {
        return impUnitLong;
    }

    public void setUnitShort(String unitShort)
    {
        this.unitShort = unitShort;
    }

    public void setUnitLong(String unitLong)
    {
        this.unitLong = unitLong;
    }

    public void setImpUnitShort(String impUnitShort)
    {
        this.impUnitShort = impUnitShort;
    }

    public void setImpUnitLong(String impUnitLong)
    {
        this.impUnitLong = impUnitLong;
    }
}
