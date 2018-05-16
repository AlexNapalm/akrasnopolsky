package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.CarBrand;

import java.util.List;

public class CarBrandDao implements IDao<CarBrand> {

    private HibernateUtil hibernateUtil = HibernateUtil.INSTANCE;

    @Override
    public CarBrand getById(int id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            CarBrand brand = session.get(CarBrand.class, id);
            return brand;
        }
    }

    @Override
    public List<CarBrand> getAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CarBrand");
            return query.list();
        }
    }

    @Override
    public void create(CarBrand model) {
    }

    @Override
    public void update(CarBrand model) {
    }

    @Override
    public void delete(CarBrand model) {
    }

    @Override
    public void close() {
        this.hibernateUtil.closeConnection();
    }
}
