package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName(value = "donate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donate {
    @TableId(value = "id")
    int id;

    @TableField(value = "donor_id")
    int donorId;

    @TableField(value = "book_name")
    String bookName;

    @TableField(value = "author")
    String author;

    @TableField(value = "publisher")
    String publisher;

    @TableField(value = "publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date publish_time;

    @TableField(value = "book_label")
    String bookLabel;

    @TableField(value = "donate_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date donateTime;

    @TableField(value = "status")
    int status;

    @TableField(value = "score")
    int score;
}
