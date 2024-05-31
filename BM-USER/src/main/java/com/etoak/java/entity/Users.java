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

/**
 * @author Bromine
 */
@TableName(value = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

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
    private int isBlock;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "score")
    private int score;

}
