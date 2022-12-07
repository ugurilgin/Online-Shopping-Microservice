package com.ugisoftware.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.TimeZone;
    @SpringBootApplication
    @EnableEurekaClient

    public class ApiGatewayApplication {
        public static void main(String[] args) {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT +0:00"));
            SpringApplication.run(ApiGatewayApplication.class, args);

        }

    }
