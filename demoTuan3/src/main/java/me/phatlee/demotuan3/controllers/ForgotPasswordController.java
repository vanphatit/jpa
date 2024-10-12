package me.phatlee.demotuan3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.phatlee.demotuan3.services.iUserService;
import me.phatlee.demotuan3.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "ForgotPasswordController", value = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {

    iUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        // get username and password from request parameter and check
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        if(username == null || email == null || username.isEmpty() || email.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
            return;
        }

        if(password == null || repassword == null || password.isEmpty() || repassword.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập mật khẩu mới!");
            request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
            return;
        }

        if(!password.equals(repassword)) {
            request.setAttribute("error", "Mật khẩu không khớp!");
            request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
            return;
        }

        if(!userService.checkExist(username, email)) {
            request.setAttribute("error", "Tài khoản hoặc email không tồn tại!");
            request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
            return;
        }
        else {
            try {
                userService.updatePassword(username, password);
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Đổi mật khẩu thất bại!");
                request.getRequestDispatcher("/view/forgot-password.jsp").forward(request, response);
                return;
            }
        }

    }
}