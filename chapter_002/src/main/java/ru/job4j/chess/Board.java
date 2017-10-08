package ru.job4j.chess;

/**
 * Класс описывает шахматную доску.
 */
public class Board {
    /**
     * Массив фигур на доске.
     */
    private Figure[] figures = new Figure[32];

    /**
     * Счетчик фигур.
     */
    private int count = 0;

    /**
     * Массив клеток, из которых состоит доска.
     */
    private Cell[][] cells = new Cell[8][8];

    /**
     * Конструктор.
     */
    public Board() {
        this.initBoard();
    }

    /**
     * Инициализация доски.
     */
    public void initBoard() {
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells.length; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Добавление фигуры на доску.
     * @param figure фигура.
     */
    public void addFigure(Figure figure) {
        this.figures[count++] = figure;
    }

    /**
     * Метод возвращает объект Cell с заданными координатами.
     * @param horizontal координата.
     * @param vertical координата.
     * @return ячейка.
     */
    public Cell findCell(int horizontal, int vertical) {
        Cell result = null;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[0].length; j++) {
                if (this.cells[i][j].getHorizontal() == horizontal && this.cells[i][j].getVertical() == vertical) {
                    result = this.cells[i][j];
                }
            }
        }
        return result;
    }

    /**
     * Геттер массива фигур.
     * @return массив фигур.
     */
    public Figure[] getFigures() {
        return this.figures;
    }

    /**
     * Геттер массива клеток.
     * @return массив клеток.
     */
    public Cell[][] getCells() {
        return this.cells;
    }

    /**
     * Ход.
     * @param source стартовая клетка.
     * @param dist целевая клетка.
     * @return true если ход возможен, false если нет.
     * @throws ImpossibleMoveException когда ход некорректен.
     * @throws OccupiedWayException когда ход через занятую клетку.
     * @throws FigureNotFoundException когда в стартовой клетке нет фигуры.
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean move = false;
        Cell cell = findCell(source.getHorizontal(), source.getVertical());
        if (!cell.isBusy()) {
            throw new FigureNotFoundException("Figure not found");
        }
        Figure figure;
        Cell[] cellsToGo;
        for (int i = 0; i < this.count; i++) {
            if (figures[i].position.equals(source)) {
                figure = figures[i];
                cellsToGo = figure.way(dist);
                for (int j = 0; j < cellsToGo.length; j++) {
                    if (cellsToGo[j].isBusy()) {
                        throw new OccupiedWayException("The way is occupied");
                    }
                }
            }
        }
        return move;
    }
}
