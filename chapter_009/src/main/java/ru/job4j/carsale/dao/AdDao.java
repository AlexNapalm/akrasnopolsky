package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.Ad;

import java.util.List;

public class AdDao implements IDao<Ad> {
    private HibernateUtil hibernateUtil = HibernateUtil.INSTANCE;

    @Override
    public Ad getById(int id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ad.class, id);
        }
    }

    @Override
    public List<Ad> getAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Ad");
            return query.list();
        }
    }

    @Override
    public void create(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void close() {
        this.hibernateUtil.closeConnection();
    }
}
