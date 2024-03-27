package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Borrow;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.mapper.BorrowMapper;
import com.etoak.java.service.IBorrowService;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@Service
public class BorrowServiceImpl
        extends ServiceImpl<BorrowMapper, Borrow>
        implements IBorrowService {
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IUserServiceFeign userServiceFeign;
    @Autowired
    IBookServiceFeign bookServiceFeign;

    @Override
    public int borrowBook(Integer userId, String bookNo) {
        /*
        * 1.判断学生的禁用情况
        * 2.判断bookNo状态
        * 3.生成一条借阅记录
        * 4.更新书的状态 -> 已借出
        */
        //1
        String baseUrl = "http://bm-user-service/users/getBlockStatus";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("userId",userId);
        String url = builder.toUriString();
        ResultVO<Integer> blockResultObj = restTemplate.getForObject(url, ResultVO.class);

        Integer blockStatus = blockResultObj.getData();
        if(blockStatus == null || blockStatus == 1){
            return 110;
        }

        ResultVO bookResultObj = bookServiceFeign.getBookStatusByNo(bookNo);
        System.out.println(bookResultObj);

        String bookStatus = (String) bookResultObj.getData();
        System.out.println(bookStatus);

        if("1".equals(bookStatus)){
            return 120;
        }
        //3
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookNo(bookNo);
        borrow.setDuration(30);
        borrow.setCreateTime(new Date());

        int result = borrowMapper.insert(borrow);
        return 200;
    }

    @Override
    public int backBook(String bookNo) {
        if(bookNo == null){
            return 101;
        }

        QueryWrapper<Borrow> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq( "book_no" ,bookNo);
        queryWrapper.orderBy(  false,false,  "create_time");
        Borrow borrow = borrowMapper.selectOne(queryWrapper);

        if(borrow == null){
            // 借阅记录为空
            return 102;
        }

        Date createTime = borrow.getCreateTime();
        Date returnTime = new Date();
        long fromt = createTime.getTime();
        long tot = returnTime.getTime();
        int days = (int) ((tot - fromt) / (1000 * 60 * 60 * 24));
        //初始状态为正常归还
        int state = 1;

        if(days > borrow.getDuration())
        {
            //逾期归还
            state = 2;
            //信用等级下调并在归零后封禁
            userServiceFeign.underCreditLevelAndBlock(borrow.getUserId(), -1);
        }

        borrow.setReturnTime(returnTime);
        borrow.setStatus(state);
        borrow.setComment(state == 1 ? "正常归还" : "逾期归还");

        bookServiceFeign.updateBookStateByNo(bookNo, 0);
        borrowMapper.updateById(borrow);

        return 200;
    }
}
