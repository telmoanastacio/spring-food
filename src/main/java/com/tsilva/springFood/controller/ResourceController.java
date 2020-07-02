package com.tsilva.springFood.controller;

import com.tsilva.springFood.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Telmo Silva on 02.07.2020.
 */

@Controller
@RequestMapping("/res")
public class ResourceController
{
	private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/{resourceName}.{fileExtension}", method = {RequestMethod.GET})
	public void showAccessDeniedMapping(
			HttpServletResponse response,
			@PathVariable(name = "resourceName") String resourceName,
			@PathVariable(name = "fileExtension") String fileExtension)
	{
		ResponseUtils.streamImage(response, servletContext, resourceName, fileExtension);
	}
}