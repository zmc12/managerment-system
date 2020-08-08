package com.atguigu.mapper;

import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(Integer roleId);
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(Integer roleId);

    @Delete("delete from role where id=#{id}")
    void delete(Integer id);

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.atguigu.mapper.PermissionMapper.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer id);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{id}")
    Role findById(Integer id);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherRoles(Integer id);

    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> deleteOtherRoles(Integer id);


    @Delete("Delete from role_permission where roleId=#{roleId} and permissionId=#{permissionId}")
    void deletePermissionToRole(@Param("roleId")Integer roleId,@Param("permissionId") Integer permissionIds);
}
