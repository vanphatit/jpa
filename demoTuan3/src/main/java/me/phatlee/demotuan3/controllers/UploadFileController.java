package me.phatlee.demotuan3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "UploadFileController", value = "/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class UploadFileController extends HttpServlet {

    public static final String UPLOAD_DIRECTORY = "D:\\upload";
    public static final String DEFAULT_FILENAME = "default.txt";

    private static final long serialVersionUID = 1L;

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return DEFAULT_FILENAME;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = File.separator + UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ
        //String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY; //upload vào thư mục project
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists())
            uploadDir.mkdir();

        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                part.write(uploadPath + File.separator + fileName);
            }

            request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/profile?message=" + URLEncoder.encode((String) request.getAttribute("message"), "UTF-8"));
    }
}