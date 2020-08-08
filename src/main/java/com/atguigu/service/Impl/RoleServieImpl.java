package com.atguigu.service.Impl;

import com.atguigu.mapper.RoleMapper;
import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServieImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleMapper.deleteFromRole_PermissionByRoleId(id);
        roleMapper.deleteFromUser_RoleByRoleId(id);
        roleMapper.delete(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Permission> findOtherRoles(Integer id) {
        return roleMapper.findOtherRoles(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for(Integer permissionId:permissionIds){
            roleMapper.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deletePermissionToRole(Integer roleId, Integer[] permissionIds) {
        for(Integer permissionId:permissionIds){
            roleMapper.deletePermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> deleteOtherRoles(Integer id) {
        return roleMapper.deleteOtherRoles(id);
    }
}
