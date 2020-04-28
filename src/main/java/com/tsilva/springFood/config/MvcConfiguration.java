package com.tsilva.springFood.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by Telmo Silva on 27.04.2020.
 */

@Configuration
@ComponentScan(basePackages="com.tsilva")
@PropertySource("classpath:persistence-mysql.properties")
@EnableWebMvc
@EnableTransactionManagement
public class MvcConfiguration implements WebMvcConfigurer
{
	private static final Logger LOG = LoggerFactory.getLogger(MvcConfiguration.class);

	// set up variable to hold the properties
	@Autowired
	private Environment env;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/**/*.ico").addResourceLocations("/angular/");

		registry.addResourceHandler("/**/*.htm").addResourceLocations("/angular/");
		registry.addResourceHandler("/**/*.html").addResourceLocations("/angular/");
		registry.addResourceHandler("/**/*.js").addResourceLocations("/angular/");
		registry.addResourceHandler("/**/*.css").addResourceLocations("/angular/");
	}

	@Bean
	public ViewResolver getViewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public DataSource getSecurityDataSource()
	{
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try
		{
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}
		catch(PropertyVetoException e)
		{
			throw new RuntimeException(e);
		}

		LOG.info("getSecurityDataSource(): jdbc.url=" + env.getProperty("jdbc.url"));
		LOG.info("getSecurityDataSource(): jdbc.user=" + env.getProperty("jdbc.user"));

		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		// set connection pool props
		securityDataSource.setInitialPoolSize(getEnvIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getEnvIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getEnvIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getEnvIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		// create session factories
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		// set the properties
		sessionFactory.setDataSource(getSecurityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
	{
		// setup transaction manager based on session factory
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);

		return hibernateTransactionManager;
	}

	private int getEnvIntProperty(String propName) throws NumberFormatException
	{
		String propVal = env.getProperty(propName);

		if(propVal == null)
		{
			throw new NumberFormatException("property key couldn't be resolved");
		}

		return Integer.parseInt(propVal);
	}

	private Properties getHibernateProperties()
	{
		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return props;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		// configurer.enable();
	}
}
