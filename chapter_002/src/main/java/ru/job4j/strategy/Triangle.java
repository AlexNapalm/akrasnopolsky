package ru.job4j.strategy;

/**
 * Треугольник.
 */
public class Triangle implements Shape {
    /**
     * Представление треугольника в псевдографике.
     * @return строкове представление треугольника.
     */
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("X");
        pic.append(System.lineSeparator());
        pic.append("XX");
        pic.append(System.lineSeparator());
        pic.append("XXX");
        pic.append(System.lineSeparator());
        return pic.toString();
    }
}
