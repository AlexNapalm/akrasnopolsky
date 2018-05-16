package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.User;

import java.util.List;

public class UserDao implements IDao<User> {
    private HibernateUtil hibernateUtil = HibernateUtil.INSTANCE;

    @Override
    public User getById(int id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void create(User entity) {
    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void delete(User entity) {
    }

    /**
     * Checks if user is registered.
     * @param login login
     * @param password password
     * @return user.
     */
    public User isRegistered(String login, String password) {
        User user = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> users = query.list();
            if (users.size() != 0) {
                user = users.get(0);
            }
        }
        return user;
    }

    @Override
    public void close() {
        this.hibernateUtil.closeConnection();
    }
}
