package com.m.config;

import com.bstek.uflo.UfloPropertyPlaceholderConfigurer;
import com.bstek.uflo.console.UfloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class UfloConfig {

    @Bean
    public UfloPropertyPlaceholderConfigurer propertyConfigurer() {
        UfloPropertyPlaceholderConfigurer propertyConfigurer = new UfloPropertyPlaceholderConfigurer();
        Resource location = new ClassPathResource("process-config.properties");
        propertyConfigurer.setLocation(location);
        return propertyConfigurer;
    }

    @Bean
    public ServletRegistrationBean servletRegistration() {
        return new ServletRegistrationBean(new UfloServlet(), "/uflo/*");
    }
}
