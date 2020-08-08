package com.atguigu.mapper;

import com.atguigu.pojo.Travller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerMapper {



    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Travller> findByOrderId(Integer id);
}
