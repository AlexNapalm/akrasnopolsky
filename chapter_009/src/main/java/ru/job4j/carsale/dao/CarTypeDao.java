package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.CarType;

import java.util.List;

public class CarTypeDao implements IDao<CarType> {
    private HibernateUtil hibernateUtil = HibernateUtil.INSTANCE;

    @Override
    public CarType getById(int id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            CarType carcase = session.get(CarType.class, id);
            return carcase;
        }
    }

    @Override
    public List<CarType> getAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CarType");
            return query.list();
        }
    }

    @Override
    public void create(CarType model) {
    }

    @Override
    public void update(CarType model) {
    }

    @Override
    public void delete(CarType model) {
    }

    @Override
    public void close() {
        this.hibernateUtil.closeConnection();
    }
}
