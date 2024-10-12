package me.phatlee.demotuan3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.phatlee.demotuan3.entity.User;
import me.phatlee.demotuan3.services.iUserService;
import me.phatlee.demotuan3.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;

        }
        // Check cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    response.sendRedirect(request.getContextPath() + "/home");
                    return;
                }
            }
        }
        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        int roleid = request.getParameter("roleid") == null ? 1 : Integer.parseInt(request.getParameter("role"));
        String phone = request.getParameter("phone");

        if(username == null || password == null || email == null || phone == null) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            return;
        }

        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            return;
        }

        if(!password.equals(repassword))
        {
            request.setAttribute("error", "Mật khẩu không khớp!");
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleid(roleid);
        user.setPhone(phone);

        iUserService service = new UserServiceImpl();
        String errorMsg = "";

        if (service.checkExist(username, email)) {
            errorMsg = "Username hoặc Email đã tồn tại!";
            request.setAttribute("error", errorMsg);
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
            return;
        }

        try{
            service.register(username, password, email, roleid, phone, username);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            e.printStackTrace();
            errorMsg = "Đăng ký thất bại!";
            request.setAttribute("error", errorMsg);
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
    }
}