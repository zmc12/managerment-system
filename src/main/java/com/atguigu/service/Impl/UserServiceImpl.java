package com.atguigu.service.Impl;

import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userInfo = null;
        try {
            userInfo = userMapper.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
         User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
       // User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        }
        return list;
    }
}
