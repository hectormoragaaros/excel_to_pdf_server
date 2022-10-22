package org.hectormoraga.exceltopdfclient.config;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringMvcDispatcherServletInitializer implements ServletContainerInitializer {
	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

		// Create an instance of ApplicationContext
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ExcelToPdfAppConfig.class);
		// Create an instance of DispatcherServlet with the previously created
		// application context
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		// Dynamically register the DispatcherServlet with the ServletContext
		ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("course", dispatcherServlet);
		servletRegistration.setLoadOnStartup(1);
		servletRegistration.addMapping("/");
	}
}
