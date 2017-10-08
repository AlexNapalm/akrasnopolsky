package ru.job4j.chess;

/**
 * Класс описывает клетку шахматной доски.
 */
public class Cell {
    /**
     * Координата по горизонтали.
     */
    private int horizontal;
    /**
     * Координата по вертикали.
     */
    private int vertical;
    /**
     * Идентификатор занятости ячейки.
     */
    private boolean isBusy;

    /**
     * Конструктор.
     * @param horizontal координата.
     * @param vertical координата.
     */
    public Cell(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.isBusy = false;
    }

    /**
     * Геттер горизонтальной координаты.
     * @return координата.
     */
    public int getHorizontal() {
        return horizontal;
    }

    /**
     * Геттер вертикальной координаты.
     * @return координата.
     */
    public int getVertical() {
        return vertical;
    }

    /**
     * Геттер занятости ячейки.
     * @return true, если ячейка занята, и false если нет.
     */
    public boolean isBusy() {
        return this.isBusy;
    }

    /**
     * Сеттер занятости ячейки.
     * @param busy true или false.
     */
    public void setBusy(boolean busy) {
        this.isBusy = busy;
    }

    /**
     * Сравнение.
     * @param o объект для сравнения.
     * @return true, еасли объекты равны, и false если нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        if (vertical != cell.vertical) {
            return false;
        }
        return horizontal == cell.horizontal;
    }
}
