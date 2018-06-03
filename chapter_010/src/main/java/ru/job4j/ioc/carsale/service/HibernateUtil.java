package ru.job4j.ioc.carsale.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

    private final SessionFactory sessionFactory;

    public HibernateUtil() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
