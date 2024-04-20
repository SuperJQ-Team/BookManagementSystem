package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("pre_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prebook {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField(value = "person_name")
    String personName;

    @TableField(value = "book_name")
    String bookName;

    @TableField(value = "book_time")
    Date bookTime;

    @TableField(value = "email")
    String email;

    @TableField(value = "status")
    Integer status;
}
