package com.tsilva.springFood.config;


import com.tsilva.springFood.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private IUserService iUserService;
	
    @Autowired
    private AuthenticationHandler authenticationHandler;

    @Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/")
				.permitAll()
			.antMatchers("/register")
				.permitAll()
			.antMatchers("/**/*.ico")
				.permitAll()
			.antMatchers("/**/*.htm")
				.permitAll()
			.antMatchers("/**/*.html")
				.permitAll()
			.antMatchers("/**/*.js")
				.permitAll()
			.antMatchers("/**/*.js.map")
				.permitAll()
			.antMatchers("/**/*.css")
				.permitAll()
			.antMatchers("/spring-food-api/recipeDelete/**")
				.hasAuthority("ADMIN")
			.antMatchers("/res/**")
				.hasAuthority("USER")
			.antMatchers("/**")
				.hasAuthority("USER")
			.and()
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/authenticate")
				.successHandler(authenticationHandler)
				.failureHandler(authenticationHandler)
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied")
			.and()
			.csrf()
				.ignoringAntMatchers("/spring-food-api/recipeDelete/**")
				.ignoringAntMatchers("/spring-food-api/recipeSave");
	}

	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(iUserService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
}