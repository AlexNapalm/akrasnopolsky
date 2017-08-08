package ru.job4j.loop;

/**
 * Класс для задания 4.2. Создать программу вычисляющую факториал. [#193].
 */
public class Factorial {
    /**
     * Метод, вычисляющий факториал.
     * @param n число, факториал которого нужно вычислить.
     * @return факториал числа.
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
