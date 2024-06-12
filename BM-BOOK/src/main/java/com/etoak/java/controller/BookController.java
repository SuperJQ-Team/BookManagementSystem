package com.etoak.java.controller;

import com.etoak.java.entity.Book;
import com.etoak.java.entity.Order;
import com.etoak.java.service.Impl.BookServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/add")
    public ResultVO addBook(Book book){
        System.out.println(book);
        int result = bookService.addBook(book);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/delete")
    public ResultVO deleteBook(Integer id){
        int result = bookService.deleteBook(id);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed(String.valueOf(result));
        }
    }

    @RequestMapping("/list")
    public ResultVO getBooks(Book book){
        List<Book> result = bookService.getBooks(book);
        if(!result.isEmpty()) {
            return ResultVO.success(result);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/update")
    public ResultVO updateBook(Book book){
        int result = bookService.updateBook(book);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/getById")
    public ResultVO getById(Integer id){
        Book result = bookService.getBookById(id);
        if(result != null) {
            return ResultVO.success(result);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/getStatusByNo")
    public ResultVO getStatusByNo(String no){
        Book result = bookService.getBookByNo(no);
        if(result != null) {
            return ResultVO.success(result.getStatus());
        }
        else{
            return ResultVO.failed();
        }
    }

    @GetMapping("/addByOrder")
    public ResultVO addByOrder(Order order){
        int result = bookService.addByOrder(order);
        if(result > 1) {
            return ResultVO.success(result);
        }
        else{
            return ResultVO.failed();
        }
    }

    @GetMapping("/addByDonate")
    public ResultVO addByDonate(Book donate){
        int result = bookService.addBook(donate);
        if(result > 1) {
            return ResultVO.success(result);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/getBooksCountByName")
    public ResultVO getBooksCount(@RequestBody List<String> bookNames){
        System.out.println(bookNames);
        Map result = bookService.getBooksCount(bookNames);
        System.out.println(result);
        if(result != null) {
            return ResultVO.success(result);
        }else{
            return ResultVO.failed();
        }
    }

    @GetMapping("/getByNoReturnScore")
    public ResultVO addByDonate(String no){
        Integer score = bookService.getScoreWithNo(no);
        if(score != null) {
            return ResultVO.success(score);
        }
        else{
            return ResultVO.failed();
        }
    }

    @GetMapping("/exchangeBook")
    public ResultVO exchangeBook(String no){
        Integer score = bookService.exchangeBook(no);
        if(score != null) {
            return ResultVO.success(score);
        }
        else{
            return ResultVO.failed();
        }
    }
}
