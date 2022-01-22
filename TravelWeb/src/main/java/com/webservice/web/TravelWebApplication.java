package com.webservice.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TravelWebApplication {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(TravelWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelWebApplication.class, args);
	}

}
