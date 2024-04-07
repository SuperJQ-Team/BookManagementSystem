package com.etoak.java.controller;

import com.etoak.java.entity.Users;
import com.etoak.java.service.impl.UsersServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RefreshScope
public class UsersController {

    @Value("${server.port}")
    String usersPost;

    @Autowired
    UsersServiceImpl usersService;

    @RequestMapping("/add")
    public ResultVO addUsers(Users users){
        int result = usersService.addUser(users);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/list")
    public ResultVO getUsersList(Users users){
        List<Users> usersList = usersService.getUsersList(users);
        return ResultVO.success(usersList);
    }

    @RequestMapping("/delete")
    public ResultVO deleteUsers(Integer id){
        int result = usersService.deleteById(id);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/block")
    public ResultVO blockUsers(Integer id){
        int result = usersService.blockById(id);
        if(result>0) {
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/getBlockStatus")
    public ResultVO getUserBlockStatus(Integer userId){
        Users users = usersService.getUsersById(userId);

        if(users != null){
            Integer isBlock = users.getIsBlock();
            return ResultVO.success(isBlock);
        }else{
            return ResultVO.failed();
        }
    }

    @RequestMapping("/getById")
    public ResultVO getById(Integer userId){
        return ResultVO.success(usersService.getUsersById(userId));
    }

    @RequestMapping("/underCreditLevelAndBlock")
    public ResultVO underCreditLevelAndBlock(Integer userId){
        int result = usersService.updateCreditLevelAndBlock(userId,-1);
        if(result>0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed();
        }
    }
}
