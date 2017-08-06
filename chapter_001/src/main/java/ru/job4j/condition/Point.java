package ru.job4j.condition;

/**
 * Класс описывает точку в системе координат.
 */
public class Point {
    /**
     * Координата х.
     */
    private int x;
    /**
     * Координата y.
     */
    private int y;

    /**
     * Конструктор для точки в двухмерной системе координат.
     * @param x координата x.
     * @param y координата y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер для координаты х.
     * @return координата x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Геттер для координаты y.
     * @return координата y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Метод, вычисляющий, лежит ли текущая точка на функции y(x) = a * x + b.
     * @param a параметр а заданной функции.
     * @param b параметр b заданной функции.
     * @return true, если точка принадлежит функции, и false - если нет.
     */
    public boolean is(int a, int b) {
        return getY() == getX() * a + b;
    }
}
