package me.phatlee.demotuan3.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.phatlee.demotuan3.services.impl.VideoServiceImpl;
import me.phatlee.demotuan3.entity.Video;
import me.phatlee.demotuan3.services.iVideoService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static me.phatlee.demotuan3.ultities.Constants.UPLOAD_DIRECTORY;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "VideoController", value = {"/admin/videos", "/admin/video/update",
        "/admin/video/edit", "/admin/video/delete", "/admin/video/insert"})
public class VideoController extends HttpServlet {
    
    public iVideoService videoService = new VideoServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if(url.contains("/admin/videos")){

            // Lấy tham số "search" từ request
            String searchQuery = request.getParameter("search");

            // Kiểm tra xem có từ khóa tìm kiếm không
            List<Video> videos;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                // Nếu có từ khóa, tìm video phù hợp
                videos = videoService.findTitle(searchQuery);
            } else {
                // Nếu không có từ khóa tìm kiếm, lấy tất cả video
                videos = videoService.findAll();
            }

            request.setAttribute("listvideo", videos);
            request.getRequestDispatcher("/view/admin/video-list.jsp").forward(request, response);
        } else if(url.contains("/admin/video/edit")){
            Video video =
                    videoService.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("video", video);
            request.getRequestDispatcher("/view/admin/video-edit.jsp").forward(request, response);
        } else if(url.contains("/admin/video/delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            videoService.delete(id);
            response.sendRedirect( request.getContextPath() + "/admin/videoes");
        } else if(url.contains("/admin/video/insert")){
            request.getRequestDispatcher("/view/admin/video-insert.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();

        if(url.contains("/admin/video/update")){
            Video video = new Video();
            video.setVideoID(Integer.parseInt(request.getParameter("videoid")));
            video.setTitle(request.getParameter("title"));
            video.setDescription(request.getParameter("description"));
            video.setViews(0);
            video.setActive(Integer.parseInt(request.getParameter("active")));

            String oldPoster = videoService.findById(video.getVideoID()).getPosterURL();
            String oldVideoURL = videoService.findById(video.getVideoID()).getVideoURL();

            String posterFileName = "";
            String videoFileName = "";
            String uploadPath = UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                // Xử lý upload poster
                Part posterPart = request.getPart("poster");
                if(posterPart.getSize() > 0){
                    String posterName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString();
                    int index = posterName.lastIndexOf(".");
                    String ext = posterName.substring(index+1);
                    posterFileName = System.currentTimeMillis() + "." + ext;
                    posterPart.write(uploadPath + File.separator + posterFileName);
                    video.setPosterURL(posterFileName);
                } else {
                    video.setPosterURL(oldPoster);
                }

                // Xử lý upload video
                Part videoPart = request.getPart("video");
                if(videoPart.getSize() > 0){
                    String videoName = Paths.get(videoPart.getSubmittedFileName()).getFileName().toString();
                    int index = videoName.lastIndexOf(".");
                    String ext = videoName.substring(index+1);
                    videoFileName = System.currentTimeMillis() + "." + ext;
                    videoPart.write(uploadPath + File.separator + videoFileName);
                    video.setVideoURL(videoFileName);
                } else {
                    video.setVideoURL(oldVideoURL);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            videoService.update(video);
            response.sendRedirect(request.getContextPath() + "/admin/videos");

        } else if(url.contains("/admin/video/insert")){
            Video video = new Video();
            video.setTitle(request.getParameter("title"));
            video.setDescription(request.getParameter("description"));
            video.setViews(0);
            video.setActive(Integer.parseInt(request.getParameter("active")));

            String posterFileName = "";
            String videoFileName = "";
            String uploadPath = UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                // Xử lý upload poster
                Part posterPart = request.getPart("poster");
                if(posterPart.getSize() > 0){
                    String posterName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString();
                    int index = posterName.lastIndexOf(".");
                    String ext = posterName.substring(index+1);
                    posterFileName = System.currentTimeMillis() + "." + ext;
                    posterPart.write(uploadPath + File.separator + posterFileName);
                    video.setPosterURL(posterFileName);
                } else {
                    video.setPosterURL("default_poster.jpg");
                }

                // Xử lý upload video
                Part videoPart = request.getPart("video");
                if(videoPart.getSize() > 0){
                    String videoName = Paths.get(videoPart.getSubmittedFileName()).getFileName().toString();
                    int index = videoName.lastIndexOf(".");
                    String ext = videoName.substring(index+1);
                    videoFileName = System.currentTimeMillis() + "." + ext;
                    videoPart.write(uploadPath + File.separator + videoFileName);
                    video.setVideoURL(videoFileName);
                } else {
                    video.setVideoURL("default_video.mp4");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            videoService.insert(video);
            response.sendRedirect(request.getContextPath() + "/admin/videos");
        }
    }

}