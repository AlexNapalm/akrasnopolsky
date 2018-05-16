package ru.job4j.carsale.service;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateUtil {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public void closeConnection() {
        SESSION_FACTORY.close();
    }
}
