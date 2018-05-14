package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.Ad;

import java.util.List;

public class AdDao implements IDao<Ad> {

    private static final AdDao INSTANCE = new AdDao();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private AdDao() {
    }

    public static AdDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Ad getById(int id) {
        try (Session session = factory.openSession()) {
            Ad ad = session.get(Ad.class, id);
            return ad;
        }
    }

    @Override
    public List<Ad> getAll() {
        try (Session session = factory.openSession();) {
            Query query = session.createQuery("from Ad");
            return query.list();
        }
    }

    @Override
    public void create(Ad ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Ad ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Ad ad) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
