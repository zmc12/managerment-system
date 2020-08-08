package com.atguigu.service;

import com.atguigu.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    void delete(Integer id);

}
