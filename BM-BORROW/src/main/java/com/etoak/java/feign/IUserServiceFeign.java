package com.etoak.java.feign;

import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "bm-user-service")
@RequestMapping("/users")
public interface IUserServiceFeign {
    @RequestMapping("/getById")
    ResultVO getUserById(Integer id);

    @RequestMapping("/underCreditLevelAndBlock")
    ResultVO underCreditLevelAndBlock(Integer userId);
}
