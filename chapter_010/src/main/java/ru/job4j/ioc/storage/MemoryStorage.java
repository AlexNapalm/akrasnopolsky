package ru.job4j.ioc.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryStorage implements IStorageDao<User> {
    private final Map<Integer, User> users = new HashMap<>();

    /**
     * Gets user by id.
     * @param id id.
     * @return user.
     */
    @Override
    public User getById(int id) {
        return users.get(id);
    }

    /**
     * Gets all users.
     * @return list of users.
     */
    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Adds user.
     * @param user user to add.
     */
    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }

    /**
     * Updates user.
     * @param model user to update.
     */
    @Override
    public void update(User model) {
        users.replace(model.getId(), model);
    }

    /**
     * Deletes user.
     * @param id id of user to delete.
     */
    @Override
    public void delete(int id) {
        users.remove(id);
    }
}
