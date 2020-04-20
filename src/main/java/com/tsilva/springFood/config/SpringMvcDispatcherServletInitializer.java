package com.tsilva.springFood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	private static final Logger LOG = LoggerFactory.getLogger(SpringMvcDispatcherServletInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] {MvcConfiguration.class};
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/" };
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext)
	{
		final DispatcherServlet dispatcherServlet = (DispatcherServlet)
				super.createDispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		LOG.info("createDispatcherServlet(): setThrowExceptionIfNoHandlerFound = true");

		return dispatcherServlet;
	}
}