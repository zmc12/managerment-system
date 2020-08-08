package com.atguigu.controller;


import com.atguigu.pojo.SysLog;
import com.atguigu.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    @RequestMapping("/delete")
    public String delete(@RequestParam()Integer id){
        sysLogService.delete(id);
        return "redirect:findAll";
    }


    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "4") Integer size, Model model)  {
        PageHelper.startPage(page,size);
        List<SysLog> sysLogList= sysLogService.findAll();
        PageInfo pageInfo = new PageInfo(sysLogList);
        model.addAttribute("pageInfo",pageInfo);
        return "syslog-list";
    }

}
