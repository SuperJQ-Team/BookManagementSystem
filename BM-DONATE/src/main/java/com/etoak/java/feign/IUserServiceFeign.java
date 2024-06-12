package com.etoak.java.feign;


import com.etoak.java.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "bm-user-service")
public interface IUserServiceFeign {
    @RequestMapping("/users/changeScore")
    ResultVO changeScore(@RequestParam("userNo") String userNo, @RequestParam("score") Integer score);

}
