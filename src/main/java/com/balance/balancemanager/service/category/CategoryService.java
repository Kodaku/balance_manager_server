package com.balance.balancemanager.service.category;

import com.balance.balancemanager.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category findById(int theId);

    public void save(Category category);

    public void deleteById(int theId);
}
