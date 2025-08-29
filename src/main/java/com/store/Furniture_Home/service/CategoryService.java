package com.store.Furniture_Home.Service;

import com.store.Furniture_Home.Entity.Category;
import com.store.Furniture_Home.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Read all
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Read by id
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    // Update
    public Category updateCategory(Integer id, Category updated) {
        return categoryRepository.findById(id).map(cat -> {
            cat.setName(updated.getName());
            cat.setDescription(updated.getDescription());
            return categoryRepository.save(cat);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    // Delete
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    // Browse (names only) - useful for public browse endpoint
    public List<String> browseCategoryNames() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
