package com.atguigu.mapper;

import com.atguigu.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface OrdersMapper {

    @Delete("delete from product where id=#{productId}")
    void delete_product(Integer productId);

    @Delete("delete from member where id=#{memberId}")
    void delete_member(Integer memberId);

    @Delete("delete from traveller where id=#{travellerId}")
    void delete_travller(Integer travellerId);

    @Delete("delete from orders where id=#{id}")
    void delete(Integer id);

    @Delete("delete from order_traveller where orderId=#{id}")
    void delete_orders_travller(Integer id);

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.atguigu.mapper.ProductMapper.findById")),
    })
    public List<Orders> findAll();


    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.atguigu.mapper.ProductMapper.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.atguigu.mapper.MemberMapper.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.atguigu.mapper.TravellerMapper.findByOrderId"))
    })
    Orders findById(Integer id);


    @Insert("insert into orders(orderNum,peopleCount,payType,orderStatus,productId,memberId,orderTime) values(#{orderNum},#{peopleCount},#{payType},#{orderStatus},#{id},#{id},#{orderTime})")
    void save_order(Orders orders);

  @Insert("insert into order_traveller(orderId,travellerId) values(#{id},#{id})")
  void save1(Orders orders);

    @Insert("insert into member(name,nickName,phoneNum,email) values(#{name},#{nickname},#{phoneNum},#{email})")
    void save_member(Member member);

    @Insert("insert into traveller(name,sex,phoneNum,credentialsType,credentialsNum,travellerType) values(#{name},#{sex},#{phoneNum},#{credentialsType},#{credentialsNum},#{travellerType})")
    void save_travller(Travller travller);
}
