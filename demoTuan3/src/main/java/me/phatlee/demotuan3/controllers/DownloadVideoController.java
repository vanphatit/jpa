package me.phatlee.demotuan3.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.phatlee.demotuan3.ultities.Constants;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/video") // ?fname=example.mp4
public class DownloadVideoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fileName = req.getParameter("fname");
        File file = new File(Constants.UPLOAD_DIRECTORY + "/" + fileName);

        resp.setContentType("video/mp4");

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                IOUtils.copy(fis, resp.getOutputStream());
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
        }
    }
}
