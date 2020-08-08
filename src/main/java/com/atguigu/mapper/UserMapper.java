package com.atguigu.mapper;

import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserMapper {


    @Select("select * from role where id in (select roleId from users_role where userId=#{id})")
    List<Role> deleteOtherRoles(Integer id);

    @Delete("delete from users_role where userId=#{userId} and roleId=#{roleId}")
    void deleteRoleToUser(@Param("userId")Integer userId, @Param("roleId")Integer roleId);


    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")Integer userId,  @Param("roleId")Integer roleIds);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(Integer id);

    @Delete("delete from users_role where userId=#{id}")
    void delete_user_role(Integer id);

    @Delete("delete from users where id=#{id}")
    void delete(Integer id);

    @Update("update users set password=#{password} where username=#{username}")
    void updateByUser(Users users);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.atguigu.mapper.RoleMapper.findRoleByUserId"))
    })
    Users findById(Integer id);

    @Select("select * from users where username=#{username}")
    public Users selectByUser(String username);



    @Select("select * from users")
    public List<Users> findAll();


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(Users users);




    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.atguigu.mapper.RoleMapper.findRoleByUserId"))
    })
    public Users findByUsername(String username) throws Exception;

}
