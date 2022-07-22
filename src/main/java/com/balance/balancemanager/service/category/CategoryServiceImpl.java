package com.balance.balancemanager.service.category;

import com.balance.balancemanager.dao.category.CategoryDAO;
import com.balance.balancemanager.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    @Transactional
    public Category findById(int theId) {
        return categoryDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        categoryDAO.deleteById(theId);
    }
}
