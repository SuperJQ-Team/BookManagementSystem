package com.etoak.java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/order")
public class OrderController {
    /**
     * 1.增删查改
     * 2.订单的审批：
     * 通过 -> 已通过  ->  往图书表中添加对应数量的书籍数据（book_no生成一下 B20240403184523+随机四位数字）
     * 拒绝 -> 已拒绝  ->  不需要往图书表中加数据
     */

}
