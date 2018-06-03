package ru.job4j.ioc.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.ioc.models.CarBrand;

import java.util.List;

public class CarBrandDao implements IDao<CarBrand> {
    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public CarBrand getById(int id) {
        try (Session session = this.factory.openSession()) {
            CarBrand brand = session.get(CarBrand.class, id);
            return brand;
        }
    }

    @Override
    public List<CarBrand> getAll() {
        try (Session session = this.factory.openSession()) {
            Query query = session.createQuery("from CarBrand");
            return query.list();
        }
    }

    @Override
    public void create(CarBrand carBrand) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.save(carBrand);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(CarBrand carBrand) {
    }

    @Override
    public void delete(CarBrand carBrand) {
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
