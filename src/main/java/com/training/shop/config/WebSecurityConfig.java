package com.training.shop.config;

import com.training.shop.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;
    private static final String[] ALLOWED_METHODS = new String[]{"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"};
    private static final String MAPPING = "/**";
    private static final String ALLOWED_ORIGINS = "*";
    private static final String ALLOWED_HEADERS = "*";
    private static final Boolean ALLOW_CREDENTIALS = true;

    public WebSecurityConfig(UserService userService) {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(MAPPING)
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders(ALLOWED_HEADERS)
                .allowCredentials(ALLOW_CREDENTIALS)
                .maxAge(MAX_AGE_SECS);
    }
}
