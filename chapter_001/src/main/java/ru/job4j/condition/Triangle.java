package ru.job4j.condition;

/**
 * Класс, описывающий треугольник.
 */
public class Triangle {
    /**
     * Точка, задающая вершину а.
     */
    private Point a;
    /**
     * Точка, задающая вершину b.
     */
    private Point b;
    /**
     * Точка, задающая вершину с.
     */
    private Point c;

    /**
     * Конструктор для треугольника.
     * @param a вершина а.
     * @param b вершина b.
     * @param c вершина с.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод, вычисляющий длину стороны треугольника.
     * @param a вершина а.
     * @param b вершина b.
     * @return длину стороны.
     */
    private double lengthSide(Point a, Point b) {
        return Math.hypot(a.getX() - b.getX(),
                a.getY() - b.getY());
    }

    /**
     * Метод, проверяющий существование треугольника.
     * @return true если треуголшьник существует и false если нет.
     */
    public boolean validate() {
        double sideAB = lengthSide(a, b);
        double sideAC = lengthSide(a, c);
        double sideBC = lengthSide(b, c);

        return ((sideAB < sideAC + sideBC)
                && (sideAC < sideAB + sideBC)
                && (sideBC < sideAB + sideAC));
    }

    /**
     * Метод, вычисляющий площадь по формуле Гаусса.
     * @return площадь треугольника, заданного декартовыми координатами.
     */
    public double area() {
        double result = 1D / 2 * Math.abs(a.getX() * b.getY() + b.getX() * c.getY() + c.getX() * a.getY()
                                 - b.getX() * a.getY() - c.getX() * b.getY() - a.getX() * c.getY());
        return result;
    }
}
