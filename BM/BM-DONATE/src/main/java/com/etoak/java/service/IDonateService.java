package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Donate;

import java.util.List;

public interface IDonateService extends IService<Donate> {
    int addDonate(Donate donate);

    int deleteDonate(Integer id);

    List<Donate> getDonate(Donate donate);

    int updateDonate(Donate donate);

    int allowDonate(Integer id);

    int notAllowDonate(Integer id);

    int exchangeBook(String userNo, String bookNo);
}