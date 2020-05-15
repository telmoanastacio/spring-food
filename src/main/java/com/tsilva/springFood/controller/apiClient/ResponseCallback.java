package com.tsilva.springFood.controller.apiClient;

/**
 * Created by Telmo Silva on 07.12.2019.
 */

public interface ResponseCallback<T>
{
    void success(T t);
    void failure(Throwable t);
}