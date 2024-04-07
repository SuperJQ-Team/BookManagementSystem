package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BM-BOOK-SERVICE",path = "/book")
public interface IBookServiceFeign {
    @RequestMapping("/getBookByNo")
    public ResultVO getBookByNo(@RequestParam(value = "bookNo") String BookNo);
    @RequestMapping("/bookBorrow")
    public ResultVO bookBorrow(@RequestParam(value = "bookBorrow") String BookNo);
    @RequestMapping("/bookReturn")
    public ResultVO bookReturn(@RequestParam(value = "bookReturn") String BookNo);
}
