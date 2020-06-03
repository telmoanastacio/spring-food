package com.tsilva.springFood.controller.apiServer.contract;

import java.io.Serializable;

/**
 * Created by Telmo Silva on 04.06.2020.
 */

public class RecipeSaveResponse implements Serializable
{
    private static final long serialVersionUID = 42865132230032L;

    private Integer status = null;
    private String message = null;
    private Long timeStamp = null;

    public RecipeSaveResponse(Integer status, String message, Long timeStamp)
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
