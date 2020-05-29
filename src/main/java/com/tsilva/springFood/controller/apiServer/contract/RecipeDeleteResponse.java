package com.tsilva.springFood.controller.apiServer.contract;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 29.05.2020.
 */

public class RecipeDeleteResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230031L;

    private Integer status = null;
    private String message = null;
    private Long timeStamp = null;

    public RecipeDeleteResponse(Integer status, String message, Long timeStamp)
    {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    public Long getTimeStamp()
    {
        return timeStamp;
    }
}
