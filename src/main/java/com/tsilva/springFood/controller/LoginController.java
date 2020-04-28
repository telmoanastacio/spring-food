package com.tsilva.springFood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Controller
public class LoginController
{
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/access-denied", method = {RequestMethod.GET})
	public String showAccessDeniedMapping()
	{
		return "access-denied";
	}
}