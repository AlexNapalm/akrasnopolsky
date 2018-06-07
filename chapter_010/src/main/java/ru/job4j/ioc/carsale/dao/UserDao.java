package ru.job4j.ioc.carsale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.ioc.carsale.repository.UserDataRepository;
import ru.job4j.ioc.models.User;

import java.util.List;

public class UserDao implements IDao<User> {
    @Autowired
    UserDataRepository repository;

    @Override
    public User getById(int id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.repository.findAll();
    }

    @Override
    public void create(User user) {
        this.repository.save(user);
    }

    @Override
    public void update(User user) {
        this.repository.save(user);
    }

    @Override
    public void delete(User user) {
        this.repository.delete(user);
    }

    /**
     * Checks if user is registered.
     * @param login login
     * @param password password
     * @return user.
     */
    public User isRegistered(String login, String password) {
        return this.repository.findByLoginAndPassword(login, password);
    }

    @Override
    public void close() {
    }
}
