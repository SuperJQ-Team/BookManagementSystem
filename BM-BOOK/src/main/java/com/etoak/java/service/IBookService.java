package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Book;
import com.etoak.java.entity.Order;

import java.util.List;
import java.util.Map;

public interface IBookService extends IService<Book>{
    int addBook(Book book);

    int deleteBook(Integer bookId);

    List<Book> getBooks(Book book);

    int updateBook(Book book);

    Book getBookById(Integer id);

    Book getBookByNo(String bookNo);

    int addByOrder(Order order);

    Map getBooksCount(List<String> booksName);

    Integer getScoreWithNo(String no);
}
