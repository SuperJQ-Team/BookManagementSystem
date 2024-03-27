package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    public int updateBookStateByNo(@Param(value = "no")String no,@Param(value = "state")Integer state);
}
