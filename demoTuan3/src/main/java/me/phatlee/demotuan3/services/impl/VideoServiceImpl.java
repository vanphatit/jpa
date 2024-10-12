package me.phatlee.demotuan3.services.impl;

import me.phatlee.demotuan3.dao.iVideoDAO;
import me.phatlee.demotuan3.dao.impl.VideoDAOimpl;
import me.phatlee.demotuan3.entity.Video;
import me.phatlee.demotuan3.services.iVideoService;

import java.util.List;

public class VideoServiceImpl implements iVideoService {

    public iVideoDAO videoDAO = new VideoDAOimpl();

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoDAO.findById(id);
    }

    @Override
    public void insert(Video video) {
        videoDAO.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDAO.update(video);
    }

    @Override
    public void delete(int id) {
        videoDAO.delete(id);
    }

    @Override
    public List<Video> findTitle(String keyword) {
        return videoDAO.findTitle(keyword);
    }

    @Override
    public void updateStatus(int id, int status) {
        videoDAO.updateStatus(id, status);
    }

    public static void main(String[] args) {
        VideoServiceImpl videoService = new VideoServiceImpl();
        List<Video> videos = videoService.findAll();
        for (Video video : videos) {
            System.out.println(video);
        }
    }
}
