package me.phatlee.demotuan3.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.phatlee.demotuan3.entity.Category;
import me.phatlee.demotuan3.services.iCategoryService;
import me.phatlee.demotuan3.services.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static me.phatlee.demotuan3.ultities.Constants.UPLOAD_DIRECTORY;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "CategoryController", value = {"/admin/categories", "/admin/category/update",
        "/admin/category/edit", "/admin/category/delete", "/admin/category/insert"})
public class CategoryController extends HttpServlet {

    public iCategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if(url.contains("/admin/categories")){
            List<Category> categories = categoryService.findAll();
            request.setAttribute("listcate", categories);
            request.getRequestDispatcher("/view/admin/category-list.jsp").forward(request, response);
        } else if(url.contains("/admin/category/edit")){
            Category category =
                    categoryService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("cate", category);
            request.getRequestDispatcher("/view/admin/category-edit.jsp").forward(request, response);
        } else if(url.contains("/admin/category/delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            categoryService.delete(id);
            response.sendRedirect( request.getContextPath() + "/admin/categories");
        } else if(url.contains("/admin/category/insert")){
            request.getRequestDispatcher("/view/admin/category-insert.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if(url.contains("/admin/category/update")){
            Category category = new Category();
            category.setCategoryID(Integer.parseInt(request.getParameter("categoryid")));
            category.setCategoryName(request.getParameter("categoryname"));
            category.setStatus(Integer.parseInt(request.getParameter("status")));

            String oldImage = categoryService.findById(category.getCategoryID()).getImages();

            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part filePart = request.getPart("images");
                if(filePart.getSize() > 0){
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index+1);
                    fname = System.currentTimeMillis() + "." + ext;
                    filePart.write(uploadPath + File.separator + fname);
                    category.setImages(fname);
                }
                else {
                    category.setImages(oldImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            categoryService.update(category);
            response.sendRedirect( request.getContextPath() + "/admin/categories");
        } else if(url.contains("/admin/category/insert")){
            Category category = new Category();
            category.setCategoryName(request.getParameter("categoryname"));
            category.setStatus(Integer.parseInt(request.getParameter("status")));
            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part filePart = request.getPart("images");
                if(filePart.getSize() > 0){
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index+1);
                    fname = System.currentTimeMillis() + "." + ext;
                    filePart.write(uploadPath + File.separator + fname);
                    category.setImages(fname);
                }
                else {
                    category.setImages("avatar.jpg");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            categoryService.insert(category);
            response.sendRedirect( request.getContextPath() + "/admin/categories");
        }
    }
}