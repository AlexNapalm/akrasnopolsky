package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

/**
 * Класс описывающий конвертацию списка юзеров в мап юзеров.
 */
public class UserConvert {
    /**
     * Конвертация листа юзеров в мап юзеров с ключом в виде id.
     * @param list лист юзеров.
     * @return мап юзеров с id в качестве ключа.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
/**
 * Класс описывает пользователя.
 */
class User {
    /**
     * id пользователя.
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Город пользователя.
     */
    private String city;
    /**
     * Конструктор.
     * @param id id.
     * @param name имя.
     * @param city город.
     */
    User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    /**
     * Геттер для id.
     * @return id.
     */
    public int getId() {
        return this.id;
    }
    /**
     * Сеттер для id.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Сеттер для имени.
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Геттер для города.
     * @return город.
     */
    public String getCity() {
        return this.city;
    }
    /**
     * Сеттер для города.
     * @param city город.
     */
    public void setCity(String city) {
        this.city = city;
    }
}
