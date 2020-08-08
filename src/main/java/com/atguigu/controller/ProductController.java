package com.atguigu.controller;


import com.atguigu.pojo.Product;
import com.atguigu.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;


    @RequestMapping("/exSave")
    public String exSave(Product product){

        productService.update(product);

        return "redirect:findAll";
    }

    @RequestMapping("/update")
    public String update(@RequestParam()Integer id,Model model){

        Product product=productService.findById(id);
        model.addAttribute("product",product);
        return "product-update";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam()Integer id){
        productService.delete(id);
        return "redirect:findAll";
    }



    @RequestMapping(value = "/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }

    @RequestMapping(value = "/findAll")
    public String findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4") Integer size,Model model){
        PageHelper.startPage(page,size);
        List<Product> all = productService.findAll();
        PageInfo pageInfo = new PageInfo(all);
        model.addAttribute("pageInfo",pageInfo);


        return "product-list";
    }


    @RequestMapping("/init")
    public String init(){
        return "main";
    }



    @RequestMapping("/save1")
    public String save1(){
        return "product-add";
    }



}
