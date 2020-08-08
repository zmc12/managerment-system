package com.atguigu.mapper;


import com.atguigu.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ProductMapper {


    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus}  where id=#{id} ")
    void update(Product product);

    @Delete("delete from product where id=#{id}")
    void delete(Integer id);


    @Select("select * from product where id=#{id}")
    public Product findById(Integer id);

    @Select("select * from product")
    public List<Product> findAll();


    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
}
