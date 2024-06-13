package com.etoak.java.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Users;
import com.etoak.java.mapper.UsersMapper;
import com.etoak.java.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class UsersServiceImpl
    extends ServiceImpl<UsersMapper, Users>
    implements IUsersService {
    @Autowired
    UsersMapper usersMapper;
    /**
     * 依赖注入
     * 控制反转
     *      Student 类型->Student 对象 stu
     *      Student stu = new Student();
     *      对象的创建由程序员控制
     *      现在控制反转
     *      Spring Boot
     *      将Student类交给SpringBoot管理，如果需要Student对象，
     *      程序员直接给SpringBoot要
     *
     * @param users
     * @return
     */
    @Override
    public int addUser(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public List<Users> getUserList(Users users) {
        //组装查询条件
        //1.根据姓名查询用户
        QueryWrapper params = new QueryWrapper<>();
        /**
         * !users.getName().equals("") -> 有可能会出现空指针异常
         */
        if(users.getName() != null && !"".equals(users.getName())) {
            params.like("name", users.getName());
        }
        if(users.getCollege() != null && !"".equals(users.getCollege())){
            params.like("college",users.getCollege());
        }
        if(users.getGender() != null){
            params.eq("gender",users.getGender());
        }

        return usersMapper.selectList(params);
    }

    @Override
    public int deleteById(Integer id) {
        return usersMapper.deleteById(id);
    }

    @Override
    public int blockUser(Integer id) {
        return usersMapper.blockUser(id);
    }

    @Override
    public Users getUserById(Integer id) {
        Users users = usersMapper.selectById(id);
        return users;
    }
    @Override
    public int updateUserCreditLevel(Integer userId,Integer changeLevel){
        Users currentUser = getUserById(userId);
        Integer currentCreditLevel = Integer.parseInt(currentUser.getCreditLevel());
        if(currentCreditLevel + changeLevel < 0){
            //降级并且当前信用等级已经为0 -> 禁用账户
            blockUser(userId);
            return 229;
        }
        //更新信用等级
        int updateResult = usersMapper.updateUserCreditLevel(userId,changeLevel);
        //信用等级已更新
        return 200;


    }
    @Override
    public int changeScore(String userNo, int score) {
        if(score < 0) {
            QueryWrapper<Users> parms = new QueryWrapper<Users>();
            parms.eq("stu_no", userNo);

            Users users = usersMapper.selectOne(parms);
            if(users.getScore() < -score){
                return -1;
            };
        }
        usersMapper.changeScore(userNo, score);
        return 1;
    }

    @SentinelResource(value = "commonResource")
    public String commonResource(String from){
        return ("公共资源被调用了，调用来源："+from);
    }

}
