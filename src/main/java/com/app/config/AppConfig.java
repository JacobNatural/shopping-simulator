package com.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring configuration class for setting up component scanning and property sources.
 */
@Configuration
@ComponentScan(basePackages = "com.app")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppConfig {
    // Add any additional configuration beans or methods here if needed
}