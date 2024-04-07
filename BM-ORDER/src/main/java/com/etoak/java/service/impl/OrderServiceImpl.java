package com.etoak.java.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Book;
import com.etoak.java.entity.Order;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.mapper.OrderMapper;
import com.etoak.java.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Order> get(Order order) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();

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

        if (allow == 0) {
            /* 未通过 */
            order.setApprovalTime(null);
            order.setStatus(0);
        }
        else if (allow == 1) {
            /* 已通过 */
            order.setApprovalTime(new Date());
            order.setStatus(1);

            System.out.println(order);

            Book book = new Book();
            book.setBookName(order.getBookName());
            book.setBookLabel(order.getBookLabel());
            book.setAuthor(order.getAuthor());
            book.setPublisher(order.getPublisher());
            book.setPublishTime(order.getPublishTime());

            Random rand = new Random();
            int MAX = 9999, MIN = 1;

            if(order.getBookNumbers() == null || order.getBookNumbers() <= 0) {
                /* 在booknumber为null或《=0时设为1 */
                order.setBookNumbers(1);
            }

            for(int i=0;i<order.getBookNumbers();++i){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String book_no = sdf.format(new Date()) + (rand.nextInt(MAX - MIN + 1) + MIN);

                book.setBookNo(book_no);
                System.out.println(book);
                // 实体类转json字符串 需要引入fastjson依赖
                String jsonString = JSON.toJSONString(book);
                // json字符串转map
                Map params = JSON.parseObject(jsonString, Map.class);

                bookServiceFeign.addBook(params);
            }

        }
        else if (allow == 2) {
            /* 已驳回 */
            order.setApprovalTime(new Date());
            order.setStatus(2);
        }
        int result =  orderMapper.updateById(order);
        if (result > 0){
            return 200;
        }
        else{
            return 470;
        }
    }

}
