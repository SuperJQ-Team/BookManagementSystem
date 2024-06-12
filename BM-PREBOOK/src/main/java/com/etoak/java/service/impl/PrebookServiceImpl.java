package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Prebook;
import com.etoak.java.mapper.PrebookMapper;
import com.etoak.java.service.IPrebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrebookServiceImpl
        extends ServiceImpl<PrebookMapper, Prebook>
        implements IPrebookService {
    @Autowired
    PrebookMapper prebookMapper;

    @Override
    public int add(Prebook prebook) {
        return prebookMapper.insert(prebook);
    }

    @Override
    public List<Prebook> list(){
        QueryWrapper<Prebook> wrapper = new QueryWrapper<>();

        return prebookMapper.selectList(wrapper);
    }

}
