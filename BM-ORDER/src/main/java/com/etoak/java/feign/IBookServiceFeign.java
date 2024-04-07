package com.etoak.java.feign;


import com.etoak.java.entity.Book;
import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="bm-book-service")
public interface IBookServiceFeign {
    @RequestMapping("/book/add")
    ResultVO addBook(Book book);
}
