package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct(){
        List<Product> allProduct = productService.findAllProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);

    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductByName(@RequestParam String keyword){
        Product productByName = productService.findProductByName(keyword);
        return new ResponseEntity<>(productByName, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        if(productService.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
