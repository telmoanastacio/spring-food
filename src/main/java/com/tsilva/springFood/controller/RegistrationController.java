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
import org.springframework.web.bind.annotation.*;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Controller
@RequestMapping("/register")
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

	@PostMapping("/processRegistrationForm")
	public String processRegistrationFormMapping(
			@ModelAttribute("crmUser") CrmUser crmUser,
			Model model)
	{
		String userName = crmUser.getUserName();
		LOG.info("processRegistrationForm(): Processing registration form for: " + userName);

		// check the database if user already exists
        User user = userService.findByUserName(userName);
        if(user != null)
        {
        	model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name already exists.");

			LOG.warn("processRegistrationForm(): User name already exists.");
        	return "registration-form";
        }
        // create user account
        userService.save(crmUser);

		LOG.info("processRegistrationForm(): Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}