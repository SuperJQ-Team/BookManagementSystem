package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Book;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    int addBooks(@Param("books") List<Book> books);
    List<Map<String, Object>> getBooksCount(@Param("booksName")List<String> books);

    int exchangeBook(@Param("no") String no);
}
