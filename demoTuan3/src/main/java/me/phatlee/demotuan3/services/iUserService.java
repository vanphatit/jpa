package me.phatlee.demotuan3.services;

import me.phatlee.demotuan3.entity.User;

public interface iUserService {
    User checkLogin(String username, String password);
    User findByUsername(String username);
    void register(String username, String password, String email, int roleid, String phone, String fullname);
    boolean checkExist(String username, String email);
    void updatePassword(String username, String password);
    void update(User user);
    void delete(String username);
}
