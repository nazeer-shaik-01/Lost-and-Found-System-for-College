package com.college.lostandfoundapi.config;

import com.college.lostandfoundapi.security.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // Explicitly define the AuthFilter bean
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    // Register the AuthFilter with Spring's filter chain
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>(authFilter);
        registration.setOrder(1); // Ensures this filter executes early
        return registration;
    }
}
