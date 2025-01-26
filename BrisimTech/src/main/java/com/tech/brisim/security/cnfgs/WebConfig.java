package com.tech.brisim.security.cnfgs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("http://localhost:8200") // Adjust this to your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify the allowed methods
                .allowCredentials(true);
    }
}
