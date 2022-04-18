package com.api.poltrona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@SpringBootApplication()
@ComponentScan
public class PoltronaApplication extends SpringBootServletInitializer{


	public static void main(String[] args) {
		SpringApplication.run(PoltronaApplication.class, args);
	}
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(PoltronaApplication.class);
	    }


}