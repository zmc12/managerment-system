package com.atguigu.controller;


import com.atguigu.pojo.*;
import com.atguigu.service.OrdersService;
import com.atguigu.service.ProductService;
import com.atguigu.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @Autowired
    private ProductService productService;




    @RequestMapping(value = "/insert")
    public String insert(Member member, Travller travller){

        Orders orders = new Orders();
        String uuid=UUID.randomUUID().toString();
        orders.setId(member.getId());
        orders.setOrderNum(uuid.substring(0,7));
        orders.setOrderTime(new Date());
        orders.setPayType(1);
        orders.setOrderStatus(1);
        ordersService.save(member,travller);
        ordersService.save1(orders);
        return "redirect:findAll";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "id",required = true)Integer id){
        ordersService.delete(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public String findById(@RequestParam(name = "id",required = true)Integer id, Model model){
        Orders orders=ordersService.findById(id);
        model.addAttribute("orders",orders);
        return "orders-show";
    }

//    @RequestMapping("/findAll")
//    public String findAll(Model model) {
//        List<Orders> all = ordersService.findAll();
//
//        model.addAttribute("ordersList", all);
//        return "orders-list";
//    }

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4") Integer size,Model model){
        PageHelper.startPage(page,size);
        List<Orders> all= ordersService.findAll();
        PageInfo pageInfo = new PageInfo(all);
        model.addAttribute("pageInfo",pageInfo);
        return "orders-page-list";
    }

    @RequestMapping("/save1")
    public String save1(Model model){
        List<Product> all = productService.findAll();
        model.addAttribute("product",all);
        return "orders-add";
    }



}
