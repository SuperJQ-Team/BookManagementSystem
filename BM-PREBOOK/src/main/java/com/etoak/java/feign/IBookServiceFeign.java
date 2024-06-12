package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@FeignClient(value = "bm-book-service")
public interface IBookServiceFeign {
    @RequestMapping("/book/getBooksCountByName")
    ResultVO<Map<String, Integer>> getBooksCount(ArrayList<String> BookNames);
}
