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

import java.util.Date;

@TableName(value = "borrow")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("stu_no")
    private String stuNo;

    @TableField("book_no")
    private String bookNo;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField("duration")
    private Integer duration;

    @TableField("status")
    private Integer status;

    @TableField("comment")
    private String comment;

    @TableField("return_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;
}
