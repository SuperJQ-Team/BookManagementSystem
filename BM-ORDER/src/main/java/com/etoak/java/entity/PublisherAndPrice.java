package com.etoak.java.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherAndPrice {
    @TableField("publisher")
    String publisher;

    @TableField("total_price")
    BigDecimal totalPrice;
}
