package com.etoak.java.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.etoak.java.entity.Users;
import com.etoak.java.handler.UserSentinelBlockHandler;
import com.etoak.java.service.impl.UsersServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15596
 */
@RestController
@RequestMapping("/users")
@RefreshScope
public class UserController {
    @Value("${server.port}")
    String port;

    @Value("${wx.appId}")
    String appId;

    @Autowired
    UsersServiceImpl usersService;

    /**
     * 接口
     * 拦截HTTP请求
     * @param
     * @return
     */
    @RequestMapping("/add")
    public ResultVO addUser(Users users){
        int result = usersService.addUser(users);
        if(result>0){
            return ResultVO.success(null);
        }
        else{
            return ResultVO.failed();
        }
    }
    @SentinelResource(value = "getUserList",blockHandlerClass = UserSentinelBlockHandler.class,
    blockHandler = "blockHandler")
    @RequestMapping("/list")
    public ResultVO getUserList(Users users){
        return ResultVO.success(usersService.getUserList(users));
    }
    @RequestMapping("/delete")
    public ResultVO deleteById(Integer id){
        int result = usersService.deleteById(id);
        if(result > 0){
            return ResultVO.success(null);
        }else {
            return ResultVO.failed();
        }
    }
    @RequestMapping("/block")
    public ResultVO blockUser(Integer id){
        int result = usersService.blockUser(id);
        if(result > 0){
            return ResultVO.success(null);
        }else{
            return ResultVO.failed();
        }
    }
@RequestMapping("/getById")
    public ResultVO getUserById(Integer id){
        return ResultVO.success(usersService.getUserById(id));
    }

    /**
     * 根据用户id获取禁用状态
     * @param userId
     * @return
     */
    @RequestMapping("getBlockStatus")
    public ResultVO getUserBlockStatus(Integer userId){
        //打印以下当前的端口号
        System.out.println("获取用户禁用状态" + port);
        //查询用户信息
        Users users = usersService.getUserById(userId);
        //获取用户禁用状态
        Integer blockStatus = users.getIsBlock();
        return ResultVO.success(blockStatus);
    }

    /**
     * 更新用户信用等级的接口
     * @param userId
     * @param changeLevel
     * @return
     */
    @RequestMapping(value = "/updateUserCreditLevel")
    public ResultVO updateUserCreditLevel(Integer userId,Integer changeLevel){
        int result = usersService.updateUserCreditLevel(userId,changeLevel);
        if(result == 229){
            return ResultVO.failed("信用等级不足，账户已禁用！");
        }
        return ResultVO.success(null);
    }

    public void testConfigRefresh(){
        System.out.println("当前appId的配置为："+appId);
    }
    @RequestMapping("/entrance1")
    public String entrance1(){
        return usersService.commonResource("entrance1");
    }
    @RequestMapping("/entrance2")
    public String entrance2(){
        return usersService.commonResource("entrance2");
    }
    @RequestMapping("/changeScore")
    public ResultVO changeScore(String userNo, Integer score) {
        if (score != null) {
            int result = usersService.changeScore(userNo, score);
            if (result > 0) {
                return ResultVO.success(null);
            }
        }
        return ResultVO.failed();
    }
}
