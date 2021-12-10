package com.traffic.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/js/**")
                .addResourceLocations("./resources/js/");
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("/static/css/");
        registry
                .addResourceHandler("/*.html", "/*.jpg")
                .addResourceLocations("/static/");
        }
}

