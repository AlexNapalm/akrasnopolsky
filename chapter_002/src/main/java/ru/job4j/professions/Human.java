package ru.job4j.professions;

/**
 * Класс описывает человека.
 */
public class Human {
    /**
     * Имя.
     */
    private String name;

    /**
     * Возраст.
     */
    private int age;

    /**
     * Адрес.
     */
    private String address;

    /**
     * Национальность.
     */
    private String nationality;

    /**
     * Дефолтный конструктор.
     */
    public Human() {
    }

    /**
     * Конструктор.
     * @param name имя.
     */
    public Human(String name) {
        this.name = name;
    }

    /**
     * Основной конструктор.
     * @param name имя.
     * @param age возраст.
     * @param address адрес.
     * @param nationality национальность.
     */
    public Human(String name, int age, String address, String nationality) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.nationality = nationality;
    }

    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
    }

    //public void eat (Food food) {
        // some implementation
    //}

    /**
     * Спать.
     */
    public void sleep() {
        //some implementation
    }
}
