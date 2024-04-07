package com.etoak.java.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import com.etoak.java.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl
        extends ServiceImpl < BookMapper, Book >
        implements IBookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public int deleteBook(Integer bookId) {
        if(bookId == null){
            return 0;
        }
        return bookMapper.deleteById(bookId);
    }

    @Override
    public List<Book> getBooks(Book book) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();

        if(book.getBookName() != null && !"".equals(book.getBookName())){
            wrapper.like("book_name", book.getBookName());
        }

        if(book.getAuthor() != null && !"".equals(book.getAuthor())){
            wrapper.like("author", book.getAuthor());
        }

        if(book.getBookNo() != null && !"".equals(book.getBookNo())){
            wrapper.like("book_no", book.getBookNo());
        }

        if(book.getPublisher() != null && !"".equals(book.getPublisher())){
            wrapper.like("publisher", book.getPublisher());
        }

        if(book.getBookLabel() != null && !"".equals(book.getBookLabel())){
            String[] labels = book.getBookLabel().split(",");
            for(String s : labels){
                if(!"".equals(s)) {
                    wrapper.like("book_label", s);
                }
            }
        }

        return bookMapper.selectList(wrapper);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public Book getBookById(Integer id) {
        if(id == null){
            return null;
        }
        return bookMapper.selectById(id);
    }

    @Override
    public Book getBookByNo(String bookNo) {
        if(bookNo == null || "".equals(bookNo)){
            return null;
        }

        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("book_no", bookNo);

        return bookMapper.selectOne(wrapper);
    }
}
