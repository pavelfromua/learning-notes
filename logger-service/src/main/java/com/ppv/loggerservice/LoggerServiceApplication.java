package com.ppv.loggerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoggerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerServiceApplication.class, args);
    }

}
