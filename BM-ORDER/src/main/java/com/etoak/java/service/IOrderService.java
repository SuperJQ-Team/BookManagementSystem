package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Order;
import com.etoak.java.entity.PublisherAndPrice;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService extends IService<Order> {
    List<Order> get(Order order);

    int add(Order order);

    int delete(Integer id);

    int update(Order order);

    int examine(Integer id, Integer allow);
    BigDecimal getPublisherPrice(String publisher);
    List<PublisherAndPrice> getSumPrice();
}
