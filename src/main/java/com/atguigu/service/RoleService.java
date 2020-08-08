package com.atguigu.service;

import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    void delete(Integer id);

    Role findById(Integer id);

    List<Permission> findOtherRoles(Integer id);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    void deletePermissionToRole(Integer roleId, Integer[] permissionIds);

    List<Permission> deleteOtherRoles(Integer id);
}
