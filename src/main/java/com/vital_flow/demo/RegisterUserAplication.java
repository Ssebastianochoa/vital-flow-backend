package com.vital_flow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.vital_flow.demo"})
public class RegisterUserAplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisterUserAplication.class, args);
	}
}
