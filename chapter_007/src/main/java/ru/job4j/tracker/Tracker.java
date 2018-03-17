package ru.job4j.tracker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс трекер - это обертка над массивом.
 */
public class Tracker {
    /**
     * Controller for interacting with databse.
     */
    DatabaseController controller;

    /**
     * Constructs new Tracker.
     * @param controller db controller.
     */
    public Tracker(DatabaseController controller) {
        this.controller = controller;
    }

    /**
     * Adds item.
     * @param title title.
     * @param description description.
     * @return generated id.
     */
    public int add(String title, String description) {
        return this.controller.addItem(title, description);
    }

    /**
     * Updates item.
     * @param id id.
     * @param title title.
     * @param description description.
     */
    public void update(int id, String title, String description) {
        controller.updateItem(id, title, description);
    }

    /**
     * Deletes item.
     * @param id id.
     */
    public void delete(int id) {
        this.controller.deleteItem(id);
    }

    /**
     * Returns all items.
     * @return all items.
     */
    public ResultSet findAll() {
        return this.controller.findAllItems();
    }

    /**
     * Returns items by title.
     * @param title title.
     * @return items.
     */
    public ResultSet findByTitle(String title) {
        return this.controller.findByTitle(title);
    }

    /**
     * Returns items by id.
     * @param id id.
     * @return item.
     */
    public ResultSet findById(int id) {
        return this.controller.findById(id);
    }
}
