package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategory();

    Category findCategoryByType(String keyword);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    Boolean deleteCategory(Long id);
}
