package com.inventory.service;

import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Category;
import com.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
       Optional<Category> category = categoryRepository.findById(categoryId);
       return category.orElseThrow(() -> new ResourceNotFoundException("Category with id=[" + categoryId + "] not found"));
    }

    public Category createCategory(Category category) {
      Optional<Category> categoryFound = categoryRepository.findCategoryByName(category.getName());
      if (categoryFound.isEmpty()) {
          return  categoryRepository.save(category);
      }
      throw new ResourceNotFoundException("Category with name=[" + category.getName() + "] already exists");
    }

    public void deleteCategoryById(Long categoryId) {
        this.getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategoryById(Category category, Long categoryId) {
        Category categoryFound = this.getCategoryById(categoryId);
        categoryFound.setName(category.getName());
        categoryFound.setDescription(category.getDescription());
        categoryFound.setPicture(category.getPicture());
        return categoryRepository.save(categoryFound);
    }

}
