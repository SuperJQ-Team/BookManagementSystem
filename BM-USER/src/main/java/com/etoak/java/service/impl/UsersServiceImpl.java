package com.etoak.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.java.entity.Users;
import com.etoak.java.mapper.UsersMapper;
import com.etoak.java.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bromine
 *
 */
@Service
public class UsersServiceImpl
        extends ServiceImpl<UsersMapper,Users>
        implements IUsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public int addUser(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public List<Users> getUsersList(Users users) {
        // 组装查询条件
        QueryWrapper<Users> parms = new QueryWrapper<Users>();

        // 1.根据姓名查询
        if(users.getName() != null && !"".equals(users.getName())){
            parms.like("name", users.getName());
        }
        // 2.根据学院查询
        if(users.getCollege() != null && !"".equals(users.getCollege())){
            parms.like("college", users.getCollege());
        }
        // 3.根据性别查询
        if(users.getGender() != null){
            parms.eq("gender", users.getGender());
        }
        // 4.根据专业查询
        if(users.getMajor() != null && !"".equals(users.getMajor())){
            parms.like("major", users.getMajor());
        }
        // 5.根据班级查询
        if(users.getClazz() != null && !"".equals(users.getClazz())){
            parms.like("clazz", users.getClazz());
        }
        // 6.根据年级查询
        if(users.getGrade() != null && !"".equals(users.getGrade())){
            parms.like("grade", users.getGrade());
        }
        // 7.根据学号查询
        if(users.getStuNo() != null && !"".equals(users.getStuNo())){
            parms.like("stu_no", users.getStuNo());
        }
        // 8.根据No查询
        if(users.getIdNo() != null && !"".equals(users.getIdNo())){
            parms.like("id_no", users.getIdNo());
        }
        // 8.根据信用等级查询
        if(users.getCreditLevel() != null && !"".equals(users.getCreditLevel())){
            parms.like("credit_level", users.getCreditLevel());
        }

        return usersMapper.selectList(parms);
    }

    @Override
    public int deleteById(Integer id) {
        return usersMapper.deleteById(id);
    }

    @Override
    public int blockById(Integer id) {
        return usersMapper.blockById(id);
    }

    @Override
    public Users getUsersById(Integer id) {
        return usersMapper.selectById(id);
    }

    @Override
    public int updateCreditLevelAndBlock(Integer userId, int changeValue) {
        Users users = usersMapper.selectById(userId);
        int creditLevel = Integer.parseInt(users.getCreditLevel());

        creditLevel += changeValue;
        if(creditLevel < 0){
            users.setCreditLevel(String.valueOf(0));
            users.setIsBlock(1);
        }else{
            users.setCreditLevel(String.valueOf(creditLevel));
        }

        return usersMapper.updateById(users);
    }
}
