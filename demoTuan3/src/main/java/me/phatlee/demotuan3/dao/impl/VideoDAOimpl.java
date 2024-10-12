package me.phatlee.demotuan3.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import me.phatlee.demotuan3.configs.JPAConfig;
import me.phatlee.demotuan3.dao.iVideoDAO;
import me.phatlee.demotuan3.entity.Video;

import java.util.List;

public class VideoDAOimpl implements iVideoDAO {

    @Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.persist(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.merge(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            Video video = enma.find(Video.class, id);
            enma.remove(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public List<Video> findTitle(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Video c WHERE c.title LIKE :videoTitle";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("videoTitle", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public void updateStatus(int id, int status) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            Video video = enma.find(Video.class, id);
            video.setActive(status);
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Video findById(int videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Video c";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Video c";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Video c";
        TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
        return query.getSingleResult().intValue();
    }
}
