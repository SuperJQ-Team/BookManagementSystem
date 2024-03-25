package com.etoak.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.java.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Bromine
 *
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    int blockById(@Param(value = "id")Integer id);
}
