package ru.job4j.ioc.carsale.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.ioc.models.User;

import java.util.List;

public class UserDao implements IDao<User> {
    private SessionFactory factory;

    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User getById(int id) {
        try (Session session = this.factory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void create(User user) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(User user) {
    }

    /**
     * Checks if user is registered.
     * @param login login
     * @param password password
     * @return user.
     */
    public User isRegistered(String login, String password) {
        try (Session session = this.factory.openSession()) {
            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            return (User) query.uniqueResult();
        }
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
