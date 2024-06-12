package com.etoak.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class BmPrebookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BmPrebookApplication.class);
    }
}