package com.atguigu.service.Impl;

import com.atguigu.mapper.PermissionMapper;
import com.atguigu.pojo.Permission;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public void save(Permission permission) {

        permissionMapper.save(permission);
    }

    @Override
    public void delete(Integer id) {
        permissionMapper.deleteFromRole_Permission(id);
        permissionMapper.delete(id);
    }
}
