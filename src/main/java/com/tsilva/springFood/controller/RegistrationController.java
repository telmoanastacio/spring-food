package com.tsilva.springFood.controller;

import com.tsilva.springFood.entity.User;
import com.tsilva.springFood.service.IUserService;
import com.tsilva.springFood.user.CrmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Controller
public class RegistrationController
{
	private static final Logger LOG = LoggerFactory.getLogger(MvcController.class);

    @Autowired
    private IUserService userService;
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder)
	{
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping(value = "/register", method = {POST})
	public void processRegistrationFormMapping(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("confirmpassword") String confirmPassword,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "email", required = false) String email,
			Model model,
			HttpServletResponse response)
	{
		CrmUser crmUser = new CrmUser(username, password, confirmPassword, name, surname, email);

		String userName = crmUser.getUserName();
		LOG.info("processRegistrationForm(): Processing registration form for: " + userName);

		// check the database if user already exists
        User user = userService.findByUserName(userName);
        if(user != null)
        {
        	model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exists.");

			LOG.warn("processRegistrationForm(): User name already exists.");

			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return;
        }
        // create user account
        userService.save(crmUser);

		LOG.info("processRegistrationForm(): Successfully created user: " + userName);

		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

	@RequestMapping(value = "/delete-account", method = POST)
	public void deleteAccountMapping(
			@RequestParam("username") String userName,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		LOG.info("deleteAccountMapping(): Processing deletion form for: " + userName);

		// check the database if user already exists
		User user = userService.findByUserName(userName);
		if(user != null)
		{
			userService.delete(user);

			response.setStatus(HttpServletResponse.SC_ACCEPTED);

			LOG.warn("deleteAccountMapping(): Successfully deleted account.");

			try
			{
				request.logout();
			}
			catch (ServletException e)
			{
				LOG.debug("deleteAccountMapping(): logout failed", e);
			}

			return;
		}

		LOG.info("deleteAccountMapping(): User not found.");

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
}