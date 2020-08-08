package com.atguigu.service.Impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public Users selectByUser(String username) {
        return userMapper.selectByUser(username);
    }

    @Override
    public List<Users> findAll() {

        return userMapper.findAll();
    }

    @Override
    public void save(Users users) {
        userMapper.save(users);
    }

    @Override
    public Users findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateByUser(Users users) {
        userMapper.updateByUser(users);
    }

    @Override
    public void delete(Integer id) {

        userMapper.delete_user_role(id);
        userMapper.delete(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer id) {
        return userMapper.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for(Integer roleId:roleIds){
            userMapper.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void deleteRoleToUser(Integer userId, Integer[] roleIds) {
        for(Integer roleId:roleIds){
            userMapper.deleteRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> deleteOtherRoles(Integer id) {
        return userMapper.deleteOtherRoles(id);
    }
}
