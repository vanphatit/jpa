package me.phatlee.demotuan3.services.impl;

import me.phatlee.demotuan3.dao.iCategoryDAO;
import me.phatlee.demotuan3.dao.impl.CategoryDAOimpl;
import me.phatlee.demotuan3.entity.Category;
import me.phatlee.demotuan3.services.iCategoryService;

import java.util.List;

public class CategoryServiceImpl implements iCategoryService {
    public iCategoryDAO categoryDAOimpl = new CategoryDAOimpl();
    @Override
    public List<Category> findAll() {
        return categoryDAOimpl.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAOimpl.findById(id);
    }

    @Override
    public void insert(Category category) {
        if(categoryDAOimpl.findName(category.getCategoryName()).equals(category.getCategoryName())){
            System.out.println("Category name already exists");
            return;
        }
        categoryDAOimpl.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDAOimpl.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDAOimpl.delete(id);
    }

    @Override
    public List<Category> findName(String keyword) {
        return categoryDAOimpl.findName(keyword);
    }

    @Override
    public void updateStatus(int id, int status) {
        categoryDAOimpl.updateStatus(id, status);
    }
}
