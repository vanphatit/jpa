package me.phatlee.demotuan3.dao.impl;

import jakarta.persistence.*;
import me.phatlee.demotuan3.configs.JPAConfig;
import me.phatlee.demotuan3.dao.iUserDAO;
import me.phatlee.demotuan3.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoimplData implements iUserDAO {

    @Override
    public List<User> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<User> query = enma.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try{
            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try{
            EntityManager enma = JPAConfig.getEntityManager();
            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insert(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.persist(user);
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
    public void update(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.merge(user);
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
            User user = enma.find(User.class, id);
            enma.remove(user);
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
    public List<User> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u", Long.class);
        return query.getSingleResult().intValue();
    }
}
