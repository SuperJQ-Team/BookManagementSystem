package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "order")
@Data

public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "order_no")
    String orderNo;

    @TableField(value = "create_user")
    String createUser;

    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "book_name")
    String bookName;

    @TableField(value = "publisher")
    String publisher;

    @TableField(value = "publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @TableField(value = "author")
    String author;

    @TableField(value = "book_label")
    String bookLabel;

    @TableField(value = "book_numbers")
    Integer bookNumbers;

    @TableField(value = "total_price")
    BigDecimal totalPrice;

    @TableField(value = "status")
    Integer status;

    @TableField(value = "comment")
    String comment;

    @TableField(value = "approval_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

}
