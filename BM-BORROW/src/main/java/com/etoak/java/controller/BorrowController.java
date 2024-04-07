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
    private BorrowServiceImpl borrowService;
    @Autowired
    private IUserServiceFeign userServiceFeign;

    @RequestMapping("/book")
    public ResultVO borrowBook(Integer userId, String bookNo) {

        int request = borrowService.borrowBook(userId, bookNo);
        if (request == 200) {
            return ResultVO.success(null);
        } else {
            return ResultVO.failed(String.valueOf(request));
        }
    }

    @RequestMapping("/getById")
    public ResultVO getUserById(Integer id) {
        return userServiceFeign.getUserById(id);
    }

    @RequestMapping("/back")
    public ResultVO backBook(Integer bookNo) {
        int result = borrowService.backBook(bookNo);
        if (result == 200) {
            return ResultVO.success(null);
        } else {
            return ResultVO.failed(String.valueOf(result));
        }
    }
}
