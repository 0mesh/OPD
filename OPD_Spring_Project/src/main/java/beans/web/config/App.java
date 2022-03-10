package beans.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class App implements WebApplicationInitializer {
	//entry point for our web application
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// setting up container on bootstrap in web app
		AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();

		container.register(WebConfiguration.class);
		
	    

		// register the event on ServletContext - addListener()
		//contextLoadListener- to start up and shut down Spring's root
		servletContext.addListener(new ContextLoaderListener(container));

		ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc", new DispatcherServlet(container));

		servlet.setLoadOnStartup(1); // only one instance of DispatcherServlet will be created
		servlet.addMapping("/"); // / - url - http://localhost: 8080/

	}

}
