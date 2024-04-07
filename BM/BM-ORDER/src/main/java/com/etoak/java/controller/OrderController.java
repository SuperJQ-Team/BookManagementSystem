package com.etoak.java.controller;


import com.etoak.java.entity.Order;
import com.etoak.java.service.impl.OrderServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @RequestMapping("/add")
    public ResultVO add(Order order){
        int request = orderService.add(order);
        if(request > 0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed(String.valueOf(request));
        }
    }

    @RequestMapping("/delete")
    public ResultVO delete(Integer id){
        int request = orderService.delete(id);
        if(request > 0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed(String.valueOf(request));
        }
    }

    @RequestMapping("/update")
    public ResultVO update(Order order){
        int request = orderService.update(order);
        if(request > 0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed(String.valueOf(request));
        }
    }

    @RequestMapping("/list")
    public ResultVO list(Order order){
        List<Order> request = orderService.get(order);
        if(!request.isEmpty()){
            return ResultVO.success(request);
        }else{
            return ResultVO.failed(null);
        }
    }

    @RequestMapping("/examine")
    public ResultVO examine(Integer id, Integer allow){
        int request = orderService.examine(id, allow);
        if(request == 200){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed(String.valueOf(request));
        }
    }
}