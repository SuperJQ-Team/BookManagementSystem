package com.etoak.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BmPreBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BmPreBookApplication.class);
    }
}