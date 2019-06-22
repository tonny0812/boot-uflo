package com.m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
@ImportResource(locations = {"classpath:uflo-console-context.xml"})
@ServletComponentScan
public class BootUfloApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootUfloApplication.class, args);
    }

}
