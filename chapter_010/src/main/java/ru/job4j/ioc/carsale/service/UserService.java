package ru.job4j.ioc.carsale.service;

import ru.job4j.ioc.models.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();

    void create(User user);

    void update(User user);

    void delete(User user);

    User getByLogin(String login);
}
