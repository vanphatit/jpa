package me.phatlee.demotuan3.dao;

import me.phatlee.demotuan3.entity.Video;

import java.util.List;

public interface iVideoDAO {
    void insert(Video video);
    void update(Video video);
    void delete(int id);
    List<Video> findTitle(String keyword);
    void updateStatus(int id, int status);

    Video findById(int videoId);
    List<Video> findAll();
    List<Video> findAll(int page, int pageSize);
    int count();
}
