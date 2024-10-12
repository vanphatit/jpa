package me.phatlee.demotuan3.dao.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import me.phatlee.demotuan3.configs.DBconnectMySQL;
import me.phatlee.demotuan3.configs.JPAConfig;
import me.phatlee.demotuan3.dao.iCategoryDAO;
import me.phatlee.demotuan3.entity.Category;
import me.phatlee.demotuan3.entity.User;
import me.phatlee.demotuan3.models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOimpl implements iCategoryDAO {

    @Override
    public void insert(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.persist(category);
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
    public void update(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.merge(category);
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
    public List<Category> findName(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryName LIKE :categoryName";
        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("categoryName", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public void updateStatus(int id, int status) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            Category category = enma.find(Category.class, id);
            category.setStatus(status);
            enma.merge(category);
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
    public Category findById(int categoryId) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Category.class, categoryId);
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Long> query = enma.createQuery("SELECT COUNT(c) FROM Category c", Long.class);
        return query.getSingleResult().intValue();
    }
}
