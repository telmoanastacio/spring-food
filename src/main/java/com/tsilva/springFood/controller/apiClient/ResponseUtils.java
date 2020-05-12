package com.tsilva.springFood.controller.apiClient;

import retrofit2.Response;

import java.io.IOException;

/**
 * Created by Telmo Silva on 07.12.2019.
 */

public final class ResponseUtils
{
    public static boolean isSuccess(Response response)
    {
        return response.isSuccessful();
    }

    public static Throwable buildStatusCodeThrowable(Response response)
    {
        String errorBody = "";
        try
        {
            errorBody = response.errorBody().string();
        }
        catch(IOException e) {}
        String message = response.message() + " " + errorBody;
        String detailedMessage = "Http status code not valid: " + response.code() + "\n" + message;
        return new Throwable(detailedMessage);
    }
}