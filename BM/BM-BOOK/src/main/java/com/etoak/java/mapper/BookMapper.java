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

    /**
     * 根据书籍编号查询书籍信息
     * @param bookNo
     * @return
     */
    Book getBookByNo(@Param(value = "bookNo") String bookNo);

    /**
     * 根据书籍编号更新书籍状态
     * @param bookNo
     * @param status
     * @return
     */
    int updateBookStatus(String bookNo,Integer status);
    List<Map<String, Object>> getBooksCount(@Param("booksName")List<String> books);

    int exchangeBook(@Param("no") String no);
}

