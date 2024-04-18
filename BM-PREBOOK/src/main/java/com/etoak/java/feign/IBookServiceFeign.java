package com.etoak.java.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bm-book-service")
@RequestMapping("/book")
public interface IBookServiceFeign {
}
