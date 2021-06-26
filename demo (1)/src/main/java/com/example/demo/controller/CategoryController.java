package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> getAllcategory() {
        List<Category> allcategory = categoryService.findAllCategory();
        return new ResponseEntity<>(allcategory, HttpStatus.OK);
    }

    @GetMapping("/category/search")
    public ResponseEntity<?> getCategoryByType(@RequestParam String keyword){
        Category categoryByType = categoryService.findCategoryByType(keyword);
        return new ResponseEntity<>(categoryByType, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        Category newcategory = categoryService.createCategory(category);
        return new ResponseEntity<>(newcategory, HttpStatus.OK);
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id){
        category.setId(id);
        Category updateCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        if(categoryService.deleteCategory(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
