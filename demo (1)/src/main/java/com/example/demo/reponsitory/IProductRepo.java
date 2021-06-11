package com.example.demo.reponsitory;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Long> {

    Product findByName(String name);
}
