package ru.job4j.tracker;

/**
 * Интерфейс.
 */
public interface UserAction {
    /**
     * Идентификатор пункта меню.
     * @return Идентификатор пункта меню.
     */
    int key();

    /**
     * Выполнение пункта меню.
     * @param input объект реализующий интерфейс Input.
     * @param tracker объект Tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Описание пункта меню.
     * @return Описание пункта меню.
     */
    String info();
}
