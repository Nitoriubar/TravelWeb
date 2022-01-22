package com.webservice.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelWebApplication {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(TravelWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelWebApplication.class, args);
	}

}
