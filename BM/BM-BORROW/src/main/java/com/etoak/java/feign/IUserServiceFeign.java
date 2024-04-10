package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BM-USER-SERVICE",path = "/users")
public interface IUserServiceFeign {
    @RequestMapping("/getById")
    ResultVO getUserById(@RequestParam(value = "id") Integer id);

    /**
     * 调用User服务中的 根据ID查询用户禁用状态
     */

    @RequestMapping("/getBlockStatus")
    ResultVO getBlockStatusById(@RequestParam(value = "userId") Integer userId);

    @RequestMapping("/updateUserCreditLevel")
    ResultVO updateUserCreditLevel(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "changeLevel")Integer changeLevel);
}
