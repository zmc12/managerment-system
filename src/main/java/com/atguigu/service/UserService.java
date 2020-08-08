package com.atguigu.service;

import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService  {
    public Users selectByUser(String username);

    List<Users> findAll();

    void save(Users users);

    Users findById(Integer id);

    void updateByUser(Users users);

    void delete(Integer id);

    List<Role> findOtherRoles(Integer id);

    void addRoleToUser(Integer userId, Integer[] roleIds);

    void deleteRoleToUser(Integer userId, Integer[] roleIds);

    List<Role> deleteOtherRoles(Integer id);
}
