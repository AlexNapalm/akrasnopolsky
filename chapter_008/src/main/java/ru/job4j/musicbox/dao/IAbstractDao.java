package ru.job4j.musicbox.dao;

import java.util.List;

public interface IAbstractDao<T> {

    T getById(int id);

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
