package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    Integer getSumPrice(@Param(value = "publisher")String publisher);
}
