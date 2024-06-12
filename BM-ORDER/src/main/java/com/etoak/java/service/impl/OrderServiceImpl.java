package com.etoak.java.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Book;
import com.etoak.java.entity.Order;
import com.etoak.java.entity.PublisherAndPrice;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.mapper.OrderMapper;
import com.etoak.java.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class OrderServiceImpl
        extends ServiceImpl<OrderMapper, Order>
        implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IBookServiceFeign bookServiceFeign;

    @Value("${order.nos.prefix}")
    String orderBefore;

    @Override
    public List<Order> get(Order order) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();

        if(order.getId() != null){
            wrapper.eq("id", order.getId());
        }
        if(order.getOrderNo() != null && !"".equals(order.getOrderNo())){
            wrapper.like("order_no", order.getOrderNo());
        }
        if(order.getBookName() != null && !"".equals(order.getBookName())){
            wrapper.like("book_name", order.getBookName());
        }
        if(order.getCreateUser() != null && !"".equals(order.getCreateUser())){
            wrapper.like("create_user", order.getCreateUser());
        }
        if(order.getPublisher() != null && !"".equals(order.getPublisher())){
            wrapper.like("publisher", order.getPublisher());
        }
        if(order.getAuthor() != null && !"".equals(order.getAuthor())){
            wrapper.like("author", order.getAuthor());
        }

        if(order.getBookLabel() != null && !"".equals(order.getBookLabel())){
            String[] labels = order.getBookLabel().split(",");
            for(String s : labels){
                if(!"".equals(s)) {
                    wrapper.like("book_label", s);
                }
            }
        }

        return orderMapper.selectList(wrapper);
    }

    @Override
    public int add(Order order) {
        order.setCreateTime(new Date());
        order.setStatus(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String order_no = orderBefore + sdf.format(new Date());

        if(order.getTotalPrice() == null){
            order.setTotalPrice(BigDecimal.ZERO);
        }
        if(order.getBookNumbers() == null){
            order.setBookNumbers(1);
        }

        order.setOrderNo(order_no);
        return orderMapper.insert(order);
    }

    @Override
    public int delete(Integer id) {
        if(id == null){
            return 0;
        }
        return orderMapper.deleteById(id);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public int examine(Integer id, Integer allow){
        if(id == null || allow == null){
            return 400;
        }
        Order order = orderMapper.selectById(id);
        if (order == null){
            return 450;
        }

        if(order.getStatus() == 0)
        {
            if (allow == 1) {
                /* 已通过 */
                order.setApprovalTime(new Date());
                order.setStatus(1);

                System.out.println(order);

                if (order.getBookNumbers() == null || order.getBookNumbers() <= 0) {
                    /* 在booknumber为null或《=0时设为1 */
                    order.setBookNumbers(1);
                }

                String jsonString = JSON.toJSONStringWithDateFormat(order, "yyyy-MM-dd HH:mm:ss");
                Map params = JSON.parseObject(jsonString, Map.class);

                bookServiceFeign.addBooks(params);

            }
            else if (allow == 2) {
                /* 已驳回 */
                order.setApprovalTime(new Date());
                order.setStatus(2);
            }
        }
        else{
            return 460;
        }
        int result =  orderMapper.updateById(order);
        if (result > 0){
            return 200;
        }
        else{
            return 470;
        }
    }

    @Override
    public BigDecimal getPublisherPrice(String publisher) {
        return orderMapper.getPublisherPrice(publisher);
    }

    @Override
    public List<PublisherAndPrice> getSumPrice() {return orderMapper.getSumPrice(); }

}
