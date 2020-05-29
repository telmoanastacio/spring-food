package com.tsilva.springFood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@ControllerAdvice
public class ExceptionController
{
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleException(HttpServletRequest request, HttpRequestMethodNotSupportedException e)
    {
        String address = request.getRequestURL().toString();

        LOG.debug("handleException(): Exception captured. Can't reach: " + address, e);

        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception e)
    {
        LOG.debug("handleException(): Exception captured", e);

        return "redirect:/";
    }
}