package ru.job4j.tracker;

/**
 * Класс описывающий абстрактный пункт меню.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Название пункта меню.
     */
    private String name;

    /**
     * Ключ-номер пункта меню.
     */
    private int key;

    /**
     * Дефолтный конструктор.
     */
    public BaseAction() {
    }

    /**
     * Основной конструктор.
     * @param key ключ-номер пункта меню.
     * @param name название пункта меню.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Метод для получения ключа-номера пункта меню.
     * @return ключ-номер пункта меню.
     */
    public abstract int key();

    /**
     * Выполнение.
     * @param input объект реализующий интерфейс Input.
     * @param tracker объект Tracker.
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Пункт меню как он есть.
     * @return Строка содержащая ключ-номер и название пункта меню.
     */
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер для ключа-номера.
     * @return ключ-номер.
     */
    public int getKey() {
        return this.key;
    }
}
