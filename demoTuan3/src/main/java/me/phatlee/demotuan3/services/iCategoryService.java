package me.phatlee.demotuan3.services;

import me.phatlee.demotuan3.entity.Category;

import java.util.List;

public interface iCategoryService {
    List<Category> findAll();
    Category findById(int id);
    void insert(Category category);
    void update(Category category);
    void delete(int id);
    List<Category> findName(String keyword);
    void updateStatus(int id, int status);
}
