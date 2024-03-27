package com.etoak.java.controller;

import com.etoak.java.entity.Book;
import com.etoak.java.service.Impl.BookServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping("/add")
    public ResultVO addBook(Book book){
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
    public ResultVO getById(String no){
        Book result = bookService.getBookByNo(no);
        if(result != null) {
            return ResultVO.success(result.getStatus());
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/updateBookStateByNo")
    public ResultVO updateBookStateByNo(String no, Integer state){
        int result = bookService.updateBookStateByNo(no, state);
        if(result > 0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }
}
