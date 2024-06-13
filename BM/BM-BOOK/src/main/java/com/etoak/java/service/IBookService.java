package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Book;
import java.util.List;
import java.util.Map;

public interface IBookService extends IService<Book> {
    //列表查询
    List<Book> getBookList(Book book);
    //根据主键查询
    Book getBookById(Integer id);
    //删除书籍
    int deleteById(Integer id);
    //更新书籍
    int updateBook(Book book);
    //新增书籍
    int addBook(Book book);

    /**
     * 根据书籍编号获取书籍信息
     */
    Book getBookByNo(String bookNo);
    int updateBookStatus(String bookNo,Integer status);

    Map getBooksCount(List<String> booksName);

    Integer getScoreWithNo(String no);

    Integer exchangeBook(String no);
}

