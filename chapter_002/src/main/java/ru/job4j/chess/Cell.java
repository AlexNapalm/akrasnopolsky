package ru.job4j.chess;

public class Cell {
    /**
     * Горизонтальная координата.
     */
    private int horizontal;

    /**
     * Вертикальная координата.
     */
    private int vertical;

    /**
     * Конструктор.
     * @param horizontal горизонтальная координата.
     * @param vertical вертикальная координата.
     */
    public Cell(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * Геттер для горизонтальной координаты.
     * @return координата.
     */
    public int getHorizontal() {
        return this.horizontal;
    }

    /**
     * Геттер для вертикальной координаты.
     * @return координата.
     */
    public int getVertical() {
        return this.vertical;
    }

}
