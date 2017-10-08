package ru.job4j.chess;

/**
 * Исключение, когда производится некорректный ход фигуры.
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Конструктор.
     * @param msg сообщение об ошибке.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}




