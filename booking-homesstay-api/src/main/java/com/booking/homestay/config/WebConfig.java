package com.booking.homestay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //  Set routes that allow cross domain routing
        registry.addMapping("/**")
                //  Set the domain name that allows cross domain requests
                //.allowedOrigins("*")
                // Cross domain configuration error , take .allowedOrigins Replace with .allowedOriginPatterns that will do .
                .allowedOriginPatterns("*")
                //  Whether to allow certificates （cookies）
                .allowCredentials(true)
                //  Set allowed methods
                .allowedMethods("*")
                //  Cross domain allow time
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
