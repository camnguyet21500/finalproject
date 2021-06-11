package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProduct();

    Product findProductByName(String keyword);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Boolean deleteProduct(Long id);
}
