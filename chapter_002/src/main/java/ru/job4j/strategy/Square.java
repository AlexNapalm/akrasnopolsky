package ru.job4j.strategy;

/**
 * Квадрат.
 */
public class Square implements Shape {
    /**
     * Представление квадрата в псевдографике.
     * @return строковое представление квадрата 3х3.
     */
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("XXX");
        pic.append(System.lineSeparator());
        pic.append("XXX");
        pic.append(System.lineSeparator());
        pic.append("XXX");
        pic.append(System.lineSeparator());
        return pic.toString();
    }
}
