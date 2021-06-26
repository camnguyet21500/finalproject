package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.reponsitory.CategoryRepository;
import com.example.demo.service.IProductService;
import com.example.demo.uploadfile.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final StorageService storageService;

    @Autowired
    IProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    public ProductController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProduct() {
        List<Product> allProduct = productService.findAllProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);

    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductByName(@RequestParam String keyword) {
        Product productByName = productService.findProductByName(keyword);
        return new ResponseEntity<>(productByName, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDTO product, @RequestParam("file") MultipartFile file) {
        storageService.store(file);
        System.out.println("You successfully uploaded " + file.getOriginalFilename() + "!");

        Category category = categoryRepository.findById(product.getCategoryID()).get();
        Product productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPicture(file.getOriginalFilename());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setSize(product.getSize());
        productEntity.setCategory(category);
//
        Product newProduct = productService.createProduct(productEntity);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO product) {

        Category category = categoryRepository.findById(product.getCategoryID()).get();
        Product productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPicture(product.getPicture());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setSize(product.getSize());
        productEntity.setCategory(category);


        Product updateProduct = productService.updateProduct(productEntity);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
