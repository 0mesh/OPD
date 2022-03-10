package beans.web.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"beans.web.controller"})
@Import({DatabaseConfiguration.class,CorsConfiguration.class})

public class WebConfiguration  {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewManager = new InternalResourceViewResolver();
		viewManager.setPrefix("/WEB-INF/jsp/");
		viewManager.setSuffix(".jsp");
		return viewManager;
	
	}
	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint injectionPoint) {
		System.out.println(injectionPoint.getMember().getDeclaringClass());
		return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
	
	}
