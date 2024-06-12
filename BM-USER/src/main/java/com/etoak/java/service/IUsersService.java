package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Users;

import java.util.List;

/**
 * @author Bromine
 */
public interface IUsersService extends IService<Users> {
    /*
      1.新增用户
      2.修改用户
      3.查询用户列表
      4.通过ID删除用户
      5.通过ID查询用户
      6.通过ID禁用用户
     */
    int addUser(Users users);

    List<Users> getUsersList(Users users);

    int deleteById(Integer id);

    int blockById(Integer id);

    Users getUsersById(Integer id);

    int updateCreditLevelAndBlock(Integer userId, int changeValue);

    int changeScore(String userNo, int score);
}
