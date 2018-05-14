package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.CarType;

import java.util.List;

public class CarTypeDao implements IDao<CarType> {

    private static final CarTypeDao INSTANCE = new CarTypeDao();
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    private CarTypeDao() {
    }

    public static CarTypeDao getInstance() {
        return INSTANCE;
    }

    @Override
    public CarType getById(int id) {
        try (Session session = factory.openSession()) {
            CarType carcase = session.get(CarType.class, id);
            return carcase;
        }
    }

    @Override
    public List<CarType> getAll() {
        try (Session session = factory.openSession();) {
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
        this.factory.close();
    }
}
