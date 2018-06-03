package ru.job4j.ioc.carsale.dao;

import java.util.List;

public interface IDao<T> {

    T getById(int id);

    List<T> getAll();

    void create(T model);

    void update(T model);

    void delete(T model);

    void close();
}
