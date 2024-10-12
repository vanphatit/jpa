package me.phatlee.demotuan3.services;

import me.phatlee.demotuan3.entity.Video;

import java.util.List;

public interface iVideoService {
    List<Video> findAll();
    Video findById(int id);
    void insert(Video video);
    void update(Video video);
    void delete(int id);
    List<Video> findTitle(String keyword);
    void updateStatus(int id, int status);
}
