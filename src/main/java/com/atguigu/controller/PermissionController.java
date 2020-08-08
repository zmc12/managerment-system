package com.atguigu.controller;


import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Users;
import com.atguigu.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;



    @RequestMapping("/delete")
    public String delete(@RequestParam()Integer id){
        permissionService.delete(id);
        return "redirect:findAll";
    }

    @RequestMapping("/save")
    public String save(Permission permission)  {
        permissionService.save(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "2") Integer size, Model model){
        PageHelper.startPage(page,size);
        List<Permission>permissions= permissionService.findAll();
        PageInfo pageInfo = new PageInfo(permissions);
        model.addAttribute("pageInfo",pageInfo);

        return "permission-list";
    }

    @RequestMapping("/add")
    public String add(){

        return "permission-add";
    }
}
