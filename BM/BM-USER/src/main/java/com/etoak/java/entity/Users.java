package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName(value = "users")
@Data
public class Users {
    /**
     * 指定id属性与表中的id主键关联， type=IdType.AUTO 主键是自动递增的
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 指定name属性与表name字段关联
     */
    @TableField(value = "name")
    private String name;

    @TableField(value = "stu_no")
    private String stuNo;

    @TableField(value = "college")
    private String college;

    @TableField(value = "major")
    private String major;

    @TableField(value = "clazz")
    private String clazz;

    @TableField(value = "gender")
    private Integer gender;

    @TableField(value = "grade")
    private String grade;

    @TableField(value = "id_no")
    private String idNo;

    @TableField(value = "credit_level")
    private String creditLevel;

    @TableField(value = "is_block")
    private Integer isBlock;

    /**
     * Date  java.util.Date 不是 java.sql.Date
     * y:year
     * M:Month
     * d:day
     * h:hours
     * Minutes:m
     * seconds:s
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "score")
    private int score;


}