package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.service.IBorrowService;
import com.etoak.java.entity.Borrow;
import com.etoak.java.mapper.BorrowMapper;
import com.etoak.java.vo.ResultVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Calendar;
import java.util.Date;

@Service

public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements IBorrowService {
    /**
     * 借阅接口
     *
     * @param userId
     * @param bookNo
     *
     */
    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IUserServiceFeign userServiceFeign;

    @Autowired
    IBookServiceFeign bookServiceFeign;
    public int borrowBook(Integer userId , String bookNo) {
       // String baseUrl = "http://localhost:8089/users/getBlockStatus";
        String baseUrl = "http://BM-USER-SERVICE/users/getBlockStatus";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("userId",userId);
        String url = builder.toUriString();
        ResultVO<Integer> blockResult = restTemplate.getForObject(url,ResultVO.class);
//      System.out.println("查询用户禁用状态：结果："+blockResult);
//   JSONObject blockResultObj = new JSONObject(blockResult);
//        System.out.println(blockResult.getData("data"));
        Integer blockStatus = blockResult.getData();
        if(blockStatus == 1){
            return 110;
        }

        String bookStatus = "1"; //0 在库 1 已借出
        if("1".equals(blockStatus)){
            return 120;
        }


        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookNo(bookNo);
        borrow.setCreateTime(new Date());
        borrow.setDuration(30);
        borrow.setStatus(0);
        int result = borrowMapper.insert(borrow);
        bookServiceFeign.bookBorrow(bookNo);
        return 200;
    }


    @Override
    public boolean borrowBook(Integer userId, String bookNo, Integer duration) {
        return false;
    }

    @Override
    public int returnBook(String bookNo) {
        if(bookNo == null || "".equals(bookNo)){
            //书号为空
            return 101;
        }
        //根据书号查询最近一条的借阅记录
        QueryWrapper param = new QueryWrapper();
        param.eq("book_no",bookNo);
        param.orderBy(false,false,"create_time");
        Borrow borrow = borrowMapper.selectOne(param);
        if(borrow == null){
            //未查询到借阅记录
            return 102;
        }
        Integer userId = borrow.getUserId();
        //判断是正常归还还是逾期归还
        //判断new Date（）和create_time差值>duration
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrow.getCreateTime());
        cal.add(Calendar.DATE,borrow.getDuration());
        Date ddl = cal.getTime();
        Date now = new Date();
        Integer changeLevel =0;
        if(ddl.after(now)){
            //正常归还
            borrow.setStatus(1);
            borrow.setComment("正常归还");
            //调用信用等级更新接口
            changeLevel = 1;
        }else{
            //逾期归还
            borrow.setStatus(2);
            borrow.setComment("逾期归还");
            //调用信用等级更新接口
            changeLevel = -1;
        }
        borrow.setReturnTime(now);
        borrowMapper.updateById(borrow);
        //更新信用等级
        ResultVO updateResult = userServiceFeign.updateUserCreditLevel(userId,changeLevel);
        if(updateResult.getCode() == 500){
            return 500;
        }
        bookServiceFeign.bookReturn(bookNo);
        return 200;
    }
}
