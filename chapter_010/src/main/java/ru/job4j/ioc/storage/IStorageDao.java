package ru.job4j.ioc.storage;

import java.util.List;

public interface IStorageDao<T> {
    /**
     * Gets model by id.
     * @param id id.
     * @return model.
     */
    T getById(int id);

    /**
     * Gets all models.
     * @return list of models.
     */
    List<T> getAll();

    /**
     * Adds model.
     * @param model model.
     */
    void add(T model);

    /**
     * Updates model.
     * @param model model.
     */
    void update(T model);

    /**
     * Deletes model.
     * @param id id of model to delete.
     */
    void delete(int id);
}
