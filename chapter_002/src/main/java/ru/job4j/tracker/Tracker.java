package ru.job4j.tracker;

import java.util.Random;

/**
 * Класс трекер - это обертка над массивом.
 */
public class Tracker {
    /**
     * Массив заявок.
     */
    private Item[] items = new Item[100];
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
        this.items[this.position++] = item;
        return item;
    }

    /**
     * редактирование заявок.
     * @param item заявка.
     */
    public void update(Item item) {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(item.getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * удаление заявок.
     * @param item заявка.
     */
    public void delete(Item item) {
        for (int i = 0; i < this.items.length; i++) {
           if (this.items[i].getId().equals(item.getId())) {
               System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
               this.position--;
               break;
           }
        }
    }

    /**
     * получение списка всех заявок.
     * @return массив заявок без null-элементов.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                result[i] = this.items[i];
            }
        }
        return result;
    }

    /**
     * получение списка по имени.
     * @param key имя для поиска.
     * @return массив заявок, имеющих данное имя без null-элементов.
     */
    public Item[] findByName(String key) {
        int countItems = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                countItems++;
            }
        }

        Item[] result = new Item[countItems];
        int arrayCount = 0;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(key)) {
                result[arrayCount] = item;
                arrayCount++;
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
