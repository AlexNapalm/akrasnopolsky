package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс трекер - это обертка над массивом.
 */
public class Tracker {
    /**
     * Лист заявок.
     */
    private List<Item> items = new ArrayList<>();
    /**
     * Счетчик добавленных заявок.
     */
    private int position = 0;
    /**
     * Переменная используется для генерации id.
     */
    private static final Random RN = new Random();

    /**
     * добавление заявок.
     * @param item заявка.
     * @return заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * редактирование заявок.
     * @param item заявка.
     */
    public void update(Item item) {
        for (Item element : this.items) {
            if (element.getId().equals(item.getId())) {
                this.items.set(this.items.indexOf(element), item);
                break;
            }
        }
    }

    /**
     * удаление заявок.
     * @param item заявка.
     */
    public void delete(Item item) {
        for (Item element : this.items) {
           if (element.getId().equals(item.getId())) {
               items.remove(element);
               this.position--;
               break;
           }
        }
    }

    /**
     * получение списка всех заявок.
     * @return лист заявок без null-элементов.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        result = this.items;

        return result;
    }

    /**
     * получение списка по имени.
     * @param key имя для поиска.
     * @return лист заявок, имеющих данное имя без null-элементов.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if(item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * получение заявки по id.
     * @param id id заявки.
     * @return заявка с данным id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * генерация id путем сложения текущего времени и случайного числа.
     * @return id заявки.
     */
    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
