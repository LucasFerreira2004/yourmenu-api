package com.yourmenu.yourmenu_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class YourmenuApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // carrega o .env
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(YourmenuApiApplication.class, args);
	}

}
