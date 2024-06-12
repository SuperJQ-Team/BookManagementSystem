package com.etoak.java.controller;

import com.etoak.java.entity.Donate;
import com.etoak.java.service.impl.DonateServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateServiceImpl donateService;

    @GetMapping("/add")
    public ResultVO addBook(Donate donate){
        int result = donateService.addDonate(donate);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/delete")
    public ResultVO deleteBook(Integer id){
        int result = donateService.deleteDonate(id);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed(String.valueOf(result));
        }
    }

    @RequestMapping("/list")
    public ResultVO getBooks(Donate donate){
        List<Donate> result = donateService.getDonate(donate);
        if(!result.isEmpty()) {
            return ResultVO.success(result);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/update")
    public ResultVO updateBook(Donate donate){
        int result = donateService.updateDonate(donate);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/allow")
    public ResultVO allow(Integer id){
        int result = donateService.allowDonate(id);
        if(result == 200) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/notAllow")
    public ResultVO notAllow(Integer id){
        int result = donateService.notAllowDonate(id);
        if(result > 0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/exchange")
    public ResultVO exchange(String stuNo, String bookNo){
        int result = donateService.exchangeBook(stuNo, bookNo);
        if(result == 200) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }
}
