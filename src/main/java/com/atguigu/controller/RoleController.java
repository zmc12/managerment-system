package com.atguigu.controller;


import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
@RolesAllowed("ADMIN")
public class RoleController {


    @Autowired
    private RoleService roleService;


    @RequestMapping("/deletePermissionToRole")
    public String deletePermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId, @RequestParam(name = "ids", required = true) Integer[] permissionIds)  {
        roleService.deletePermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }
    @RequestMapping("/deleteRoleByIdAndAllPermission")
    public String deleteRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)Integer id,Model model){
        Role role = roleService.findById(id);
        List<Permission> permissionList =roleService.deleteOtherRoles(id);
        model.addAttribute("role", role);
        model.addAttribute("permissionList",permissionList);
        return "role-permission-delete";
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId, @RequestParam(name = "ids", required = true) Integer[] permissionIds)  {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public String findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)Integer id,Model model){
         Role role = roleService.findById(id);
        List<Permission> permissionList =roleService.findOtherRoles(id);
        model.addAttribute("role", role);
        model.addAttribute("permissionList",permissionList);
        return "role-permission-add";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam()Integer id){
        roleService.delete(id);
        return "redirect:findAll";
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }


    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "2") Integer size, Model model){
        PageHelper.startPage(page,size);
        List<Role> roles= roleService.findAll();
        PageInfo pageInfo = new PageInfo(roles);
        model.addAttribute("pageInfo",pageInfo);
        return "role-list";
    }


    @RequestMapping("/add")
    public String add(){
        return "role-add";
    }

}
