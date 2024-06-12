package com.etoak.java.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Donate;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.mapper.DonateMapper;
import com.etoak.java.service.IDonateService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DonateServiceImpl
        extends ServiceImpl<DonateMapper, Donate>
        implements IDonateService {
    @Autowired
    DonateMapper donateMapper;
    @Autowired
    IBookServiceFeign bookServiceFeign;
    @Autowired
    IUserServiceFeign userServiceFeign;

    @Override
    public int addDonate(Donate donate) {
        donate.setId(null);
        donate.setStatus(0);
        donate.setDelFlag(false);
        donate.setDonateTime(new Date());
        return donateMapper.insert(donate);
    }

    @Override
    public int deleteDonate(Integer id) {
        Donate donate = new Donate();
        donate.setId(id);
        donate.setDelFlag(true);
        return donateMapper.updateById(donate);
    }

    @Override
    public List<Donate> getDonate(Donate donate) {
        QueryWrapper<Donate> wrapper = new QueryWrapper<>();

        if(donate.getId() != null){
            wrapper.eq("id", donate.getId());
        }

        if(donate.getBookName() != null && !"".equals(donate.getBookName())){
            wrapper.like("book_name", donate.getBookName());
        }

        if(donate.getAuthor() != null && !"".equals(donate.getAuthor())){
            wrapper.like("author", donate.getAuthor());
        }

        if(donate.getPublisher() != null && !"".equals(donate.getPublisher())){
            wrapper.like("publisher", donate.getPublisher());
        }

        if(donate.getBookLabel() != null && !"".equals(donate.getBookLabel())){
            String[] labels = donate.getBookLabel().split(",");
            for(String s : labels){
                if(!"".equals(s)) {
                    wrapper.like("book_label", s);
                }
            }
        }

        if(donate.getStatus() != null && !"".equals(donate.getBookLabel())){
            wrapper.eq("status", donate.getPublisher());
        }
        wrapper.eq("del_flag", false);
        return donateMapper.selectList(wrapper);
    }

    @Override
    public int updateDonate(Donate donate) {
        return donateMapper.updateById(donate);
    }

    @Override
    public int allowDonate(Integer id) {
        Donate donate = donateMapper.selectById(id);

        String userNo = donate.getDonorNo();
        if(userNo != null){
            userServiceFeign.changeScore(userNo, donate.getScore());
        }

        donate.setId(null);
        String jsonString = JSON.toJSONStringWithDateFormat(donate, "yyyy-MM-dd HH:mm:ss");
        Map params = JSON.parseObject(jsonString, Map.class);
        bookServiceFeign.addBook(params);

        donate.setId(id);
        donate.setStatus(1);
        int donateResult = donateMapper.updateById(donate);
        if(donateResult > 0){
            return 200;
        } else {
            return 501;
        }
    }

    @Override
    public int notAllowDonate(Integer id){
        Donate donate = new Donate();
        donate.setId(id);
        donate.setStatus(2);
        return donateMapper.updateById(donate);
    }

    @Override
    public int exchangeBook(String userNo, String bookNo) {
        ResultVO<Integer> scoreResult =  bookServiceFeign.getScoreByNo(bookNo);
        Integer score;
        if(scoreResult.getCode() == 200){
            score = scoreResult.getData();
        }
        else{
            return 501;
        }

        ResultVO result = userServiceFeign.changeScore(userNo, -score);
        if(result.getCode() == 200){
            bookServiceFeign.exchangeBook(bookNo);
            return 200;
        }else{
            return 502;
        }

    }
}
