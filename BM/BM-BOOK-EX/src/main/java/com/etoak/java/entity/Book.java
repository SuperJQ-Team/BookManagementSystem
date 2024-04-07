package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName(value = "book")
@Data
public class Book {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "book_name")
    private String bookName;

    @TableField(value = "book_no")
    private String bookNo;

    @TableField(value = "author")
    private String author;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @TableField(value = "durability")
    private Integer durability;

    @TableField(value = "storage_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date storageTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "book_label")
    private String bookLabel;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(value = "update_user")
    private String updateUser;
}
