package ru.job4j.chess;

/**
 * Исключение, когда фигура не найдена.
 */
public class FigureNotFoundException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg сообщение об ошибке.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
