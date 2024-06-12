package com.etoak.java.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Order;
import com.etoak.java.mapper.BookMapper;
import com.etoak.java.service.IBookService;
import com.etoak.java.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookServiceImpl
        extends ServiceImpl < BookMapper, Book >
        implements IBookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public int addBook(Book book) {
        if(book.getGetScore() == null) {
            book.setGetScore(1);
        }
        Random rand = new Random();
        int MAX = 9999, MIN = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDateString = sdf.format(new Date());
        String book_no = nowDateString + String.format("%04d" ,rand.nextInt(MAX - MIN + 1) + MIN);
        book.setBookNo(book_no);
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

    @Override
    public int addByOrder(Order order) {
        Random rand = new Random();
        int MAX = 9999, MIN = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDateString = sdf.format(new Date());

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < order.getBookNumbers(); ++i) {
            Book book = new Book();
            book.setBookName(order.getBookName());
            book.setBookLabel(order.getBookLabel());
            book.setAuthor(order.getAuthor());
            book.setPublisher(order.getPublisher());
            book.setPublishTime(order.getPublishTime());
            book.setStatus(0);
            book.setDurability(0);

            String book_no = nowDateString + String.format("%04d" ,rand.nextInt(MAX - MIN + 1) + MIN);
            book.setBookNo(book_no);

            books.add(book);
        }

        return bookMapper.addBooks(books);
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


    public Integer exchangeBook(String no) {
        return bookMapper.exchangeBook(no);
    }
}
