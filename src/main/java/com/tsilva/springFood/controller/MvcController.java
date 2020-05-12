package com.tsilva.springFood.controller;

import com.tsilva.springFood.controller.apiClient.ResponseCallback;
import com.tsilva.springFood.controller.apiClient.contract.recipeSearch.RecipeSearch;
import com.tsilva.springFood.controller.apiClient.request.get.GetRecipeSearch;
import com.tsilva.springFood.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Controller
public class MvcController
{
	private static final Logger LOG = LoggerFactory.getLogger(MvcController.class);

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private GetRecipeSearch getRecipeSearch;

	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public String indexMapping()
	{
		// ====================
		// remove after test
		// ====================
		getRecipeSearch.execute("bacalhau", new ResponseCallback<RecipeSearch>()
		{
			@Override
			public void success(RecipeSearch recipeSearch)
			{
				System.out.println();
			}

			@Override
			public void failure(Throwable t)
			{
				System.out.println();
			}
		});
		// ====================
		// ++++++++++++++++++++
		// ====================

		return "index";
	}

	@RequestMapping(value = "/index.html", method = {RequestMethod.GET})
	public void angularIndexMapping(HttpServletRequest request, HttpServletResponse response)
	{
		ResponseUtils.streamIndex(servletContext, request, response);
	}
}