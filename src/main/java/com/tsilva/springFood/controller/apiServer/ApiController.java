package com.tsilva.springFood.controller.apiServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@RestController
@RequestMapping("/spring-food-api")
public class ApiController
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);
}