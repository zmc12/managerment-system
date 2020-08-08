package com.atguigu.mapper;


import com.atguigu.pojo.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {


    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(Integer id) ;

    @Delete("delete from permission where id=#{id}")
    void delete(Integer id);

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(Integer id);


    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
