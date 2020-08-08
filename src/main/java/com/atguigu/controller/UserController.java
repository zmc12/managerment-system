package com.atguigu.controller;

import com.atguigu.pojo.Role;
import com.atguigu.pojo.Users;
import com.atguigu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @RolesAllowed("ADMIN")
    @RequestMapping("/deleteRoleToUser")
    public String deleteRoleToUser(@RequestParam(name = "userId", required = true) Integer userId, @RequestParam(name = "ids", required = true) Integer[] roleIds){
        userService.deleteRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/deleteUserByIdAndAllRole")
    public String deleteUserByIdAndAllRole(@RequestParam(name = "id",required = true)Integer id,Model model){
        Users user = userService.findById(id);
        List<Role>roles =userService.deleteOtherRoles(id);
        model.addAttribute("user",user);
        model.addAttribute("roleList",roles);
        return "user-role-delete";
    }



    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId, @RequestParam(name = "ids", required = true) Integer[] roleIds){
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/findUserByIdAndAllRole")
    public String findUserByIdAndAllRole(@RequestParam(name = "id",required = true)Integer id,Model model){
        Users user = userService.findById(id);
        List<Role>roles =userService.findOtherRoles(id);
        model.addAttribute("user",user);
        model.addAttribute("roleList",roles);
        return "user-role-add";
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/delete")
    public String delete(@RequestParam()Integer id){
        userService.delete(id);
        return "redirect:findAll";
    }

    @PermitAll
    @RequestMapping("/change")
    public String change(HttpServletRequest request,Model model){
        String username =  request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(username.equals("")||password2.equals("")){
            model.addAttribute("indicate","修改失败,用户名和密码不能为空");
            return "modify";
        }
        Users users = userService.selectByUser(username);
        if(password1.equals(users.getPassword())){
            users.setPassword(password2);
            userService.updateByUser(users);

            return "main";
        }else {
            model.addAttribute("indicate","修改失败，原密码错误");
            return "modify";
        }
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/findById")
    public String findById(Integer id,Model model){
       Users users= userService.findById(id);
       model.addAttribute("user",users);
       return "user-show";
    }

    @RequestMapping("/save")
    public String save(Users users){
        userService.save(users);
        return "redirect:findAll";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "4") Integer size, Model model){
        PageHelper.startPage(page,size);
        List<Users>users= userService.findAll();
        PageInfo pageInfo = new PageInfo(users);
        model.addAttribute("pageInfo",pageInfo);
        return "user-list";
    }


    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
        if(username.equals("")){
            return "login";
        }
        Users users = userService.selectByUser(username);
        if(password.equals(users.getPassword())){
//            model.addAttribute("username",username);
            request.getSession().setAttribute("username",username);
            return "main";
        }else {
            return "failer";
        }

    }



    @PermitAll
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        return "redirect:init";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/add")
    public String add(){

        return "user-add";
    }




    @PermitAll
    @RequestMapping(value = "/init")
    public String logout(){

        return "login";
    }


    @PermitAll
    @RequestMapping("/modify")
    public String modify(){
        return "modify";
    }

    @PermitAll
    @RequestMapping(value = "/head")
    public String head(){

        return "main";
    }
}
