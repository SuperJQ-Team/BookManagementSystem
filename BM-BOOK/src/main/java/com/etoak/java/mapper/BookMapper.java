package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    int addBooks(@Param("books") List<Book> books);
    List<Integer> getBooksCount(@Param("booksName")List<String> books);
}
