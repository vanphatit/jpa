package me.phatlee.demotuan3.services.impl;

import me.phatlee.demotuan3.dao.iUserDAO;
import me.phatlee.demotuan3.dao.impl.UserDaoimplData;
import me.phatlee.demotuan3.entity.User;
import me.phatlee.demotuan3.services.iUserService;

public class UserServiceImpl implements iUserService {

    iUserDAO userDAO = new UserDaoimplData();

    @Override
    public User checkLogin(String username, String password) {
        User user = userDAO.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public void register(String username, String password, String email, int roleid, String phone, String fullname) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleid(roleid);
        user.setPhone(phone);
        user.setFullname(fullname);
        userDAO.insert(user);
    }

    @Override
    public boolean checkExist(String username, String email) {
        if(userDAO.findByUsername(username) == null && userDAO.findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void updatePassword(String username, String password) {
        User user = userDAO.findByUsername(username);
        if(user != null) {
            user.setPassword(password);
            userDAO.update(user);
        }
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(String username) {
        User user = userDAO.findByUsername(username);
        if(user != null) {
            userDAO.delete(user.getId());
        }
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        System.out.println(userService.checkLogin("john_doe", "password123"));
//        System.out.println(userService.register("ghetbug", "password123@",
//                "hi@gmail.com", 2, "1234567890"));
        //System.out.println(userService.checkExist("hi", "john@example.com"));
        System.out.println(userService.findByUsername("jane_smith").getFullname());
    }
}
