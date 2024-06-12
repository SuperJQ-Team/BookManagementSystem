package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Order;
import com.etoak.java.entity.PublisherAndPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    BigDecimal getPublisherPrice(@Param(value = "publisher")String publisher);
    List<PublisherAndPrice> getSumPrice();
}
