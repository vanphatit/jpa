package me.phatlee.demotuan3.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.phatlee.demotuan3.configs.JPAConfig;
import me.phatlee.demotuan3.entity.Category;
import me.phatlee.demotuan3.entity.Role;
import me.phatlee.demotuan3.entity.User;

import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        initData();

        // if not logged in, redirect to login page
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // if logged in, show home page with username from cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                request.setAttribute("username", cookie.getValue());
                break;
            }
        }

        request.getRequestDispatcher("/view/web/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("Hello World!");

    }

    public void initData(){
        EntityManager em = JPAConfig.getEntityManager();
        em.getTransaction().begin();
        System.out.println("Initializing data");

        // Kiểm tra xem dữ liệu đã tồn tại hay chưa
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(r) FROM Role r", Long.class);
        long roleCount = query.getSingleResult();
        System.out.println("roleCount: " + roleCount);

        if (roleCount == 0) {
            // Chèn dữ liệu cho bảng Roles
            System.out.println("Inserting data for Roles");
            Role adminRole = new Role();
            adminRole.setRoleid(1);
            adminRole.setName("admin");
            em.persist(adminRole);

            Role userRole = new Role();
            userRole.setRoleid(2);
            userRole.setName("user");
            em.persist(userRole);

            // Chèn dữ liệu cho bảng Users
            User admin1 = new User();
            admin1.setUsername("ad1");
            admin1.setFullname("john_doe");
            admin1.setPassword("123");
            admin1.setEmail("john@example.com");
            admin1.setRoleid(1);  // ID của admin
            admin1.setPhone("0123456789");
            em.persist(admin1);

            User admin2 = new User();
            admin2.setUsername("ad2");
            admin2.setFullname("charlie_brown");
            admin2.setPassword("123");
            admin2.setEmail("charlie@example.com");
            admin2.setRoleid(1);
            admin2.setPhone("0765432198");
            em.persist(admin2);

            User user1 = new User();
            user1.setUsername("u1");
            user1.setFullname("jane_smith");
            user1.setPassword("123");
            user1.setEmail("jane@example.com");
            user1.setRoleid(2);
            user1.setPhone("0987654321");
            em.persist(user1);

            User user2 = new User();
            user2.setUsername("u2");
            user2.setFullname("alice_wonder");
            user2.setPassword("123");
            user2.setEmail("alice@example.com");
            user2.setRoleid(2);
            user2.setPhone("0912345678");
            em.persist(user2);

            User user3 = new User();
            user3.setUsername("u3");
            user3.setFullname("bob_builder");
            user3.setPassword("123");
            user3.setEmail("bob@example.com");
            user3.setRoleid(2);
            user3.setPhone("0856781234");
            em.persist(user3);

            // Chèn dữ liệu cho bảng Categories
            Category category1 = new Category();
            category1.setCategoryName("Phim hành động");
            category1.setImages("h1CBC93F0.jpg");
            category1.setStatus(1);
            em.persist(category1);

            Category category2 = new Category();
            category2.setCategoryName("Anime");
            category2.setImages("https://cdn.popsww.com/blog/sites/2/2023/06/thanh-guom-diet-quy-season-4.jpg");
            category2.setStatus(1);
            em.persist(category2);
        }
        System.out.println("Data initialization completed");
        em.getTransaction().commit();
        em.close();
    }
}