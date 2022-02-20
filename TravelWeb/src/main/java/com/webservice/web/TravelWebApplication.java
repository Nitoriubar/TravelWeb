package com.webservice.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.webservice.web.config.properties.AppProperties;
import com.webservice.web.config.properties.CorsProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    CorsProperties.class,
    AppProperties.class
})
public class TravelWebApplication{

	public static void main(String[] args) {
		SpringApplication.run(TravelWebApplication.class, args);
	}

}
