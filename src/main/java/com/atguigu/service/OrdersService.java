package com.atguigu.service;


import com.atguigu.pojo.Member;
import com.atguigu.pojo.Orders;
import com.atguigu.pojo.Travller;

import java.util.List;


public interface OrdersService {

    public List<Orders> findAll();

    public List<Orders> findAll(int page, int size);

    public Orders findById(Integer id);

    void delete(Integer id);

    void delete_product(Integer productId);

    void delete_member(Integer memberId);

    void delete_travller(Integer travellerId);

    void save(Member member, Travller travller);

    void save1(Orders orders);
}
