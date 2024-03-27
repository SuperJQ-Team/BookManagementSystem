package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Borrow;

public interface IBorrowService extends IService<Borrow> {
    int borrowBook(Integer userId, String bookNo);

    int backBook(String bookNo);
}
