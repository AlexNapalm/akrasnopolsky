package ru.job4j.ioc.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.ioc.models.CarType;

import java.util.List;

public class CarTypeDao implements IDao<CarType> {
    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public CarType getById(int id) {
        try (Session session = this.factory.openSession()) {
            CarType carcase = session.get(CarType.class, id);
            return carcase;
        }
    }

    @Override
    public List<CarType> getAll() {
        try (Session session = this.factory.openSession()) {
            Query query = session.createQuery("from CarType");
            return query.list();
        }
    }

    @Override
    public void create(CarType carType) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.save(carType);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(CarType carType) {
    }

    @Override
    public void delete(CarType carType) {
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
