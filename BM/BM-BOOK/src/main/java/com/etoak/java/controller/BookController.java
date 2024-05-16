package com.etoak.java.controller;


import com.etoak.java.constant.BookConstant;
import com.etoak.java.entity.Book;
import com.etoak.java.service.impl.BookServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")


public class BookController {
    @Autowired
    BookServiceImpl bookService;


    //列表查询
    @RequestMapping("/list")
    public ResultVO getBookList(Book book){
        return ResultVO.success(bookService.getBookList(book));
    }

    @GetMapping("/add")
    public ResultVO addBook(Book book){
        int result = bookService.addBook(book);
        if(result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("新增失败");
        }
    }

    @RequestMapping("/update")
    public ResultVO updateBook(Book book){
        int result = bookService.updateBook(book);
        if(result >0){
            return ResultVO.success(null);
        }else{
            return  ResultVO.failed("更新失败");
        }
    }

    @RequestMapping("/delete")
    public ResultVO deleteById(Integer id){
        int result = bookService.deleteById(id);
        if(result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed("删除失败");
        }
    }

    @RequestMapping("/getById")
    public ResultVO getBookById(Integer id){
        return ResultVO.success(bookService.getBookById(id));
    }

    @RequestMapping("/getBookByNo")
    public ResultVO getBookByNo(String bookNo){
        return ResultVO.success(bookService.getBookByNo(bookNo));
    }

    /**
     * 书籍借出接口
     * @param bookNo
     * @return
     */

    @RequestMapping("/bookBorrow")
    public ResultVO bookBorrow(String bookNo){
        int result = bookService.updateBookStatus(bookNo, BookConstant.BOOK_STATUS_YJC);
        if(result > 0){
            return ResultVO.success(null);
        }
        return ResultVO.failed("更新书籍状态失败");
    }

    @RequestMapping("/bookReturn")
    public ResultVO bookReturn(String bookNo){
        int result = bookService.updateBookStatus(bookNo,BookConstant.BOOK_STATUS_ZK);
        if(result > 0){
            return ResultVO.success(null);
        }
        return ResultVO.failed("更新书籍状态失败");
    }
}


