package com.tsilva.springFood.config;

import com.tsilva.springFood.entity.User;
import com.tsilva.springFood.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler
{
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationHandler.class);

    @Autowired
    private IUserService iUserService;

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		LOG.info("onAuthenticationSuccess(): Authentication Success");

		String userName = authentication.getName();

		LOG.debug("userName = " + userName);

		User user = iUserService.findByUserName(userName);

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		response.setStatus(HttpServletResponse.SC_ACCEPTED);

		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
		response.setHeader("_csrf", csrfToken.getToken());
	}

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{
		LOG.info("onAuthenticationSuccess(): Authentication Failure", exception);

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
