package ru.job4j.generic;

/**
 * Store interface.
 * @param <T> type of stored elements.
 */
public interface Store<T extends Base> {
    /**
     * add element.
     * @param model element to add.
     */
    void add(T model);

    /**
     * update element.
     * @param id id of the updated element.
     * @param model element to update with.
     */
    void update(String id, T model);

    /**
     * delete element.
     * @param id if of the element to delete.
     * @return true, if deleted, and false, if not.
     */
    void delete(String id);
}
