package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Order;

import java.util.List;

public interface IOrderService extends IService<Order> {
    List<Order> get(Order order);

    int add(Order order);
    int delete(Integer id);
    int update(Order order);
    int examine(Integer id, Integer allow);
    Integer getSumPrice(String publisher);
}
