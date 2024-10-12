package me.phatlee.demotuan3.dao;

import me.phatlee.demotuan3.entity.User;

import java.util.List;

public interface iUserDAO {
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
    void insert(User user);
    void update(User user);
    void delete(int id);
    List<User> findAll(int page, int pageSize);
    int count();
}
