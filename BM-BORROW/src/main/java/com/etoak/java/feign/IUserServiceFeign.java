package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bm-user-service")
public interface IUserServiceFeign {
    @RequestMapping("/users/getById")
    ResultVO getUserById(Integer id);

    @RequestMapping("/users/underCreditLevelAndBlock")
    ResultVO underCreditLevelAndBlock(Integer userId, Integer changeVal);
}
