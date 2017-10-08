package ru.job4j.chess;

/**
 * Исключение, когда на пути фигуры стоит другая фигура.
 */
public class OccupiedWayException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg сообщение об ошибке.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
