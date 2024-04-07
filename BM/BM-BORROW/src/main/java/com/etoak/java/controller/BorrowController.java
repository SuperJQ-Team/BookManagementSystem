package com.etoak.java.controller;

import com.etoak.java.feign.IUserServiceFeign;
import com.etoak.java.service.impl.BorrowServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    BorrowServiceImpl borrowService;

    @Autowired
    private IUserServiceFeign userServiceFeign;

    @RequestMapping("/borrowBook")
    public ResultVO borrowBook(Integer userId,String bookNo){
        int result = borrowService.borrowBook(userId, bookNo);
        if(result == 200){
            return ResultVO.success(null);
        } else if (result == 110) {
            return ResultVO.failed("用户账户已被禁用");
        } else if (result == 120) {
            return ResultVO.failed("书已被借出");
        }else{
            return ResultVO.failed("网络故障请联系管理员");
        }
    }

    /**
     * 根据id查询详细用户信息
     * @param id
     * @return
     */
    @RequestMapping("/getById")
    public ResultVO getUserById(Integer id){
        //调用FeignClient 向User服务发送查询请求
        return userServiceFeign.getUserById(id);
    }
    @RequestMapping("/getBlockStatus")
    public ResultVO getBlockStatusById(Integer id){
        return userServiceFeign.getBlockStatusById(id);
    }


    /**
     * 归还接口
     * 参数：bookNo
     * 查借阅记录->最近的借阅记录->根据日期判断 逾期？正常
     */
    @RequestMapping("/returnBook")
    public ResultVO returnBook(String bookNo){
        int result = borrowService.returnBook(bookNo);
        if(result == 101){
            return ResultVO.failed("书号为空");
        }else if(result == 102){
            return  ResultVO.failed("未查询到借阅记录");
        }else if(result == 500){
            return ResultVO.success("逾期还书，用户已被扣分并禁用");
        }else{
            return ResultVO.success("还书成功");
        }
    }

}

