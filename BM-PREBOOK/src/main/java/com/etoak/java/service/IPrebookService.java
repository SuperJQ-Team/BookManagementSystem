package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Prebook;

import java.util.List;

public interface IPrebookService extends IService<Prebook> {
    int add(Prebook prebook);
    List<Prebook> list();
}
