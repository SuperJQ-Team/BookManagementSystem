package com.etoak.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.java.entity.Users;

import java.util.List;

public interface IUsersService extends IService<Users> {
    /**
     * 书写常用的抽象方法
     * 1.新增用户
     * 2.修改用户
     * 3。查询用户列表
     * 4.根据主键ID删除用户
     * 5.根据主键ID查询用户
     * 6.将某个ID用户禁用的方法
     *
     * 抽象方法是没有方法体的
     * public 访问权限修饰符 为什么是灰色的
     *  因为interface所有的方法默认都是public修饰的
     *  新增用户的方法
     *  int 返回值 影响行数
     *          数据库的 insert 语句
     *
     * @return
     */
    public int addUser(Users users);

    /**
     * 获取用户列表
     * 多条件查询
     * name,stu_no,gender,college
     * @return
     */
    List<Users> getUserList(Users users);

    /**
     * 根据主键id删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);
    /**
     *
     */
    int blockUser(Integer id);

    Users getUserById(Integer id);

    /**
     * 更新用户信用等级
     * @param userId
     * @param changeLevel
     * @return
     */
    int updateUserCreditLevel(Integer userId, Integer changeLevel);
    int changeScore(String userNo, int score);
}
