package com.etoak.java.feign;


import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "BM-BOOK-SERVICE")
public interface IBookServiceFeign {
    @RequestMapping("/book/addByDonate")
    ResultVO addBook(@RequestParam("donate") Map donate);
    @RequestMapping("/book/getByNoReturnScore")
    ResultVO getScoreByNo(@RequestParam("no") String no);

    @RequestMapping("/book/exchangeBook")
    ResultVO exchangeBook(@RequestParam("no") String no);
}