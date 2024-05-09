package com.etoak.java.config;

import com.etoak.java.filter.MyGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatWayConfig {
    @Bean
    public GlobalFilter myGlobalFilter(){
        return new MyGlobalFilter();
    }
}
