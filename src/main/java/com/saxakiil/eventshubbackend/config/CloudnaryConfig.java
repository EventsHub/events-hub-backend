package com.saxakiil.eventshubbackend.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudnaryConfig {

    @Value("${app.cloudName}")
    private String cloudName;

    @Value("${app.apiKey}")
    private String apiKey;

    @Value("${app.apiSecret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudnary() {
        return new Cloudinary(Map.of(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
}