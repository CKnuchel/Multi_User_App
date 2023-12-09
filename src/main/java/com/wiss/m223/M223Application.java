package com.wiss.m223;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class M223Application {

	public static void main(String[] args) {
		SpringApplication.run(M223Application.class, args);
	}

	/**
	 * Enable Cors Requests from React Frontend
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/public").allowedOrigins("http://localhost:5173");
			}
		};
	}

}

