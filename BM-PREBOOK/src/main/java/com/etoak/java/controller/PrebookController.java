package com.etoak.java.controller;

import com.etoak.java.entity.Prebook;
import com.etoak.java.service.impl.PrebookServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrebookController {
    @Autowired
    PrebookServiceImpl prebookService;

    @RequestMapping("/prebook")
    ResultVO prebook(Prebook prebook){
        int request = prebookService.add(prebook);
        if(request > 0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed(null);
        }
    }
}
