package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import com.etoak.java.entity.Book;
import com.etoak.java.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 功能要求：
 1.实现基本的书籍的增、删、查、改
 2.结合借阅模块，完善 借书方法 -> 查一下书籍的状态
 3.结合借阅模块，完善 归还书籍的方法 -> 更新一下破损度 更新一下书籍的状态
 4.在借阅模块，加一个接口，可以多条件查询书籍列表
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {


    @Autowired
    private BookMapper bookMapper;



    @Override
    public List<Book> getBookList(Book book) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(book.getBookName())){
            queryWrapper.like("book_name",book.getBookName());
        }
        if(StringUtils.isNotEmpty(book.getAuthor())){
            queryWrapper.like("author",book.getAuthor());
        }
        if(StringUtils.isNotEmpty(book.getPublisher())){
            queryWrapper.like("publisher",book.getPublisher());
        }
        // todo 标签作为查询条件
        //存储方式：马克思，物理，哲学   前端传来的参数：马克思，哲学
        String queryLabel = book.getBookLabel();
        //根据，分割  String类的20个常用方法
        String[] labelArray = queryLabel.split(",");
        for(String s : labelArray){
            if(StringUtils.isNotEmpty(s)){
                queryWrapper.like("book_label",s);
            }

        }
        return bookMapper.selectList(queryWrapper);
    }
    @Override
    public Map getBooksCount(List<String> booksName) {
        List<Map<String, Object>> coms = bookMapper.getBooksCount(booksName);

        Map<String, Integer> map = new HashMap<>();
        for(Map<String, Object> com : coms){
            map.put((String) com.get("book_name"), Math.toIntExact((Long) com.get("count")));
        }
        return map;
    }

    @Override
    public Integer getScoreWithNo(String no) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("book_no", no);

        Book book = bookMapper.selectOne(wrapper);

        if(book == null || book.getStatus() != 0){
            return null;
        }
        else{
            return book.getGetScore();
        }
    }

    @Override
    public Integer exchangeBook(String no) {
        return bookMapper.exchangeBook(no);
    }


    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateById(book);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public Book getBookByNo(String bookNo) {
        return bookMapper.getBookByNo(bookNo);
    }

    @Override
    public int updateBookStatus(String bookNo, Integer status) {
        return bookMapper.updateBookStatus(bookNo,status);
    }


}
