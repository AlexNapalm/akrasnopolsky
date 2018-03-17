package ru.job4j.tracker;

/**
 * Исключение выхода за пределы допустимого значения при выборе пункта меню.
 */
public class MenuOutException extends RuntimeException {
    /**
     * Конструктор исключения.
     * @param msg сообщение об ошибке.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
