package com.balance.balancemanager.rest;

import com.balance.balancemanager.entity.Category;
import com.balance.balancemanager.error.exceptions.ResourceNotFoundException;
import com.balance.balancemanager.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private Category getCategoryPrivate(int categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category == null) {
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
        }
        return category;
    }

    @GetMapping("/categories")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Category getCategory(@PathVariable int categoryId) {
        return getCategoryPrivate(categoryId);
    }

    @PostMapping("/categories")
    @CrossOrigin(origins = "http://localhost:3000")
    public Category saveCategory(@RequestBody Category category) {
        category.setId(0);
        categoryService.save(category);
        return category;
    }

    @PutMapping("/categories/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Category updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        Category currentCategory = getCategoryPrivate(categoryId);

        currentCategory.setName(category.getName());
        categoryService.save(currentCategory);

        return currentCategory;
    }

    @DeleteMapping("/categories/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteCategory(@PathVariable int categoryId) {
        getCategoryPrivate(categoryId);

        categoryService.deleteById(categoryId);
        return "Category with id " + categoryId + " deleted";
    }
}
