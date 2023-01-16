package com.example.api02.config;

import com.example.api02.interceptor.TokenInterceptor;
import com.example.api02.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@EnableWebMvc
@Log4j2
@Configuration
@RequiredArgsConstructor
public class CustomServletConfig implements WebMvcConfigurer {


    private final JWTUtil jwtUtil;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        log.info("addInterceptores");

        registry.addInterceptor(new TokenInterceptor(jwtUtil)).addPathPatterns("/api/todos/*");


        log.info(registry);

    }


}
