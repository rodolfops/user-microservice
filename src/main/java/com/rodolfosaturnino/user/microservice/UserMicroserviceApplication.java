package com.rodolfosaturnino.user.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class UserMicroserviceApplication {

	private static final Logger log = LoggerFactory.getLogger(UserMicroserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.rodolfosaturnino.user.microservice.controller"))
				.paths(PathSelectors.regex("/.*"))
				.build();
	}

}
