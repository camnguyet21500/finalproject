package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.reponsitory.CategoryRepository;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByType(String keyword) {
        return (Category) categoryRepository.findByTypeContaining(keyword);
    }

    @Override
    public Category createCategory(Category categories) {
        return categoryRepository.save(categories);
    }

    @Override
    public Category updateCategory(Category categories) {
        return categoryRepository.saveAndFlush(categories);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
