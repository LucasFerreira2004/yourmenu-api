package com.yourmenu.yourmenu_api.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    @PostConstruct
    public void loadEnv() {
        Dotenv dotenv = Dotenv.load();

        dotenv.entries().forEach(entry -> {
            // Seta como variável de sistema (para Spring conseguir resolver)
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
