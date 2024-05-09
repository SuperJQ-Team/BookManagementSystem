package com.etoak.java.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，做一个模拟健全
 */
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求的对象
        ServerHttpRequest request = exchange.getRequest();
        //2.获取请求的资源路径(url)
        String requestPath = request.getURI().getPath();
        System.out.println("请求的Path:"+requestPath);
        //3.获取请求头的参数
        String token = request.getHeaders().getFirst("token");
        System.out.println("请求头中携带的token:"+token);

        if(token == null || "".equals(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
