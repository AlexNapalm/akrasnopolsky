package ru.job4j.todolist.dbmanager;

import ru.job4j.models.Item;

import java.util.List;

public interface IDbManager {

   void addOrUpdate(Item item);

   void delete(Item item);

   void close();

   List<Item> getAll();

   List<Item> getUndone();

   void toggleDone(int id, boolean done);

}
