package com.atguigu.service;


import com.atguigu.pojo.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public void save(Product product);

    void delete(Integer id);

    Product findById(Integer id);

    void update(Product product);
}
