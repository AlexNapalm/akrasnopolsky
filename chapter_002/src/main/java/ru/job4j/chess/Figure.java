package ru.job4j.chess;

/**
 * Класс описывает абстрактную фигуру.
 */
public abstract class Figure {
    /**
     * Начальная позиция фигуры.
     */
    Cell position;

    /**
     * Доска, на которой находится фигура.
     */
    Board board;

    /**
     * Конструктор.
     * @param board доска, на которой фигура.
     * @param position начальная позиция фигуры.
     */
    public Figure(Board board, Cell position) {
        this.position = position;
        position.setBusy(true);
        this.board = board;
    }

    /**
     * Ход фигуры.
     * @param dist ячейка куда следует пойти.
     * @return массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может.
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Итоговое перемещение фигуры.
     * @param dist целевая клетка.
     */
    public void clone(Cell dist) {
        this.position.setBusy(false);
        this.position = dist;
        dist.setBusy(true);
    }
}
