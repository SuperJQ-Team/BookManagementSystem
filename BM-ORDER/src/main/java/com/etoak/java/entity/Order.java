package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "order_no")
    private String orderNo;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "publish_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @TableField(value = "author")
    private String author;

    @TableField(value = "book_label")
    private String bookLabel;

    @TableField(value = "book_numbers")
    private Integer bookNumbers;

    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "comment")
    private String comment;

    @TableField(value = "approval_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

}