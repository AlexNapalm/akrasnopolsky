package ru.job4j.chess;

/**
 * Класс описывает фигуру "слон".
 */
public class Bishop extends Figure {
    /**
     * Цвет фигуры.
     */
    private String color;

    /**
     * Конструктор.
     * @param color цвет.
     * @param board доска.
     * @param position начальная позиция.
     */
    public Bishop(String color, Board board, Cell position) {
        super(board, position);
        this.color = color;
    }

    /**
     * Геттер для цвета фигуры.
     * @return цвет.
     */
    public String getColor() {
        return color;
    }

    /**
     * Ход фигуры.
     * @param dist ячейка куда следует пойти.
     * @return массив ячеек. которые должна пройти фигура.
     * @throws ImpossibleMoveException Если фигура туда пойти не может.
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell source = this.position;
        Cell[] result = new Cell[Math.abs(dist.getVertical() - source.getVertical())];
        if (Math.abs(dist.getVertical() - source.getVertical()) != Math.abs(dist.getHorizontal() - source.getHorizontal())
                || dist.equals(source)) {
            throw new ImpossibleMoveException("Figure can't move there");
        } else {
            for (int i = 0; i < result.length; i++) {
                if (dist.getVertical() - source.getVertical() > 0 && dist.getHorizontal() - source.getHorizontal() > 0) {
                    result[i] = this.board.findCell(source.getHorizontal() + 1 + i, source.getVertical() + 1 + i);
                } else if (dist.getVertical() - source.getVertical() > 0 && dist.getHorizontal() - source.getHorizontal() < 0) {
                    result[i] = this.board.findCell(source.getHorizontal() - 1 - i, source.getVertical() + 1 + i);
                } else if (dist.getVertical() - source.getVertical() < 0 && dist.getHorizontal() - source.getHorizontal() > 0) {
                    result[i] = this.board.findCell(source.getHorizontal() + 1 + i, source.getVertical() - 1 - i);
                } else if (dist.getVertical() - source.getVertical() < 0 && dist.getHorizontal() - source.getHorizontal() < 0) {
                    result[i] = this.board.findCell(source.getHorizontal() - 1 - i, source.getVertical() - 1 - i);
                }
            }
        }
        return result;
    }
}
