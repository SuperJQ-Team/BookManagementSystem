package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper

public interface UsersMapper extends BaseMapper<Users> {

    int blockUser(@Param(value = "id") Integer id);

    /**
     * 跟新用户信用等级
     * userId 要更新的用户id
     * changeLevel 信用变化的数量 1 -1 2 -2
     * @param userId
     * @param changeLevel
     * @return
     */
    int updateUserCreditLevel(@Param(value = "userId") Integer userId,@Param(value = "changeLevel") Integer changeLevel);
}
