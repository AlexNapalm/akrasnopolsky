package ru.job4j.max;

/**
 * Class Класс для вычисления наибольшего аргумента.
 * @author akrasnopolsky
 * @since 06.08.2017
 * @version 1
 */
public class Max {
    /**
     * Метод, вычисляющий наибольший из двух аргументов.
     * @param first первый аргумекнт.
     * @param second второй аргумент.
     * @return наибольший, из двух аргументов.
     */
    public int max(int first, int second) {
        return first >= second ? first : second;
    }

    /**
     * Метод, вычисляющий наибольший из трех аргументов.
     * @param first первый аргумент.
     * @param second второй аргумент.
     * @param third третий аргумент.
     * @return наибольшгий из трех аргументов.
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
