package com.tsilva.springFood.controller.apiServer.contract.recipeDetailSearchResponse;

import com.tsilva.springFood.entity.Measure;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 28.05.2020.
 */

public class MeasureResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230029L;

    private String unitShort = null;
    private String unitLong = null;
    private String impUnitShort = null;
    private String impUnitLong = null;

    public MeasureResponse(Measure measure)
    {
        if(measure != null)
        {
            this.unitShort = measure.getUnitShort();
            this.unitLong = measure.getUnitLong();
            this.impUnitShort = measure.getImpUnitShort();
            this.impUnitLong = measure.getImpUnitLong();
        }
    }

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
}
