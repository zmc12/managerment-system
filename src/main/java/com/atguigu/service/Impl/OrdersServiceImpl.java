package com.atguigu.service.Impl;


import com.atguigu.mapper.OrdersMapper;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Orders;
import com.atguigu.pojo.Travller;
import com.atguigu.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public List<Orders> findAll() {


        return  ordersMapper.findAll();
    }

    @Override
    public List<Orders> findAll(int page, int size) {

        PageHelper.startPage(page,size);
        return ordersMapper.findAll();
    }

    @Override
    public Orders findById(Integer id) {
        return ordersMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {

        ordersMapper.delete(id);
        ordersMapper.delete_orders_travller(id);
    }

    @Override
    public void delete_product(Integer productId) {
        ordersMapper.delete_product(productId);
    }

    @Override
    public void delete_member(Integer memberId) {
        ordersMapper.delete_member(memberId);
    }

    @Override
    public void delete_travller(Integer travellerId) {
        ordersMapper.delete_travller(travellerId);
    }

    @Override
    public void save(Member member, Travller travller) {


        ordersMapper.save_member(member);
        ordersMapper.save_travller(travller);

    }

    @Override
    public void save1(Orders orders) {
        ordersMapper.save_order(orders);
        ordersMapper.save1(orders);
    }
}
