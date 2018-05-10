package ru.job4j.todolist.dao;

import ru.job4j.models.Item;
import ru.job4j.todolist.dbmanager.DbManager;
import ru.job4j.todolist.dbmanager.IDbManager;
import ru.job4j.todolist.dbmanager.LambdaDecorator;
import java.util.List;

public class ItemDao {

    IDbManager db = new LambdaDecorator(DbManager.INSTANCE);

    public void addOrUpdate(Item item) {
        this.db.addOrUpdate(item);
    }

    public void delete(Item item) {
        this.db.delete(item);
    }

    public void closeConnection() {
        db.close();
    }

    public List<Item> getAll() {
        return db.getAll();
    }

    public List<Item> getUndone() {
        return db.getUndone();
    }

    public void toggleDone(int id, boolean done) {
       db.toggleDone(id, done);
    }

}
