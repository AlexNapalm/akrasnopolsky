package ru.job4j.loop;

/**
 * Класс для задания 4.1. Подсчет суммы чётных чисел в диапазоне [#192]
 */
public class Counter {
    /**
     * Метод, вычисляющий сумму четных числе в диапазоне от start до finish включительно.
     * @param start граница диапазона.
     * @param finish граница диапазона.
     * @return сумма четных чисел в диапазоне.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
