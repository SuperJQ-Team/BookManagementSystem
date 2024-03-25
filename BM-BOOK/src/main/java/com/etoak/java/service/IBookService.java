package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Book;
import java.util.List;

public interface IBookService extends IService<Book>{
    int addBook(Book book);

    int deleteBook(Integer bookId);

    List<Book> getBooks(Book book);

    int updateBook(Book book);


    Book getBookById(Integer id);

    Book getBookByNo(String bookNo);
}
