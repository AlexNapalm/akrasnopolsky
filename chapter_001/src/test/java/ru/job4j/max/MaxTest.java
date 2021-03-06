package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестовый класс.
 */
public class MaxTest {
    /**
     * Метод для тестирования метода max, когда первый аргумент меньше второго.
     */
    @Test
    public void whenFirstLessThanSecond() {
        Max maximum = new Max();
        int result = maximum.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Метод для тестирования метода max, когда первый аргумент больше второго.
     */
    @Test
    public void whenFirstBiggerThanSecond() {
        Max    maximum = new Max();
        int result = maximum.max(3, 1);
        assertThat(result, is(3));
    }

    /**
     * Метод для тестирования метода max, когда первый аргумент равен второму.
     */
    @Test
    public void whenFirstEqualsSecond() {
        Max maximum = new Max();
        int result = maximum.max(4, 4);
        assertThat(result, is(4));
    }

    /**
     * Тест метода max с 3 параметрами, когда первый аргумент больше остальных.
     */
    @Test
    public void whenFisrtBiggerOtherTwo() {
        Max maximum = new Max();
        int result = maximum.max(3, 2, 1);
        assertThat(result, is(3));
    }

    /**
     * Тест метода max с тремя параметрами, когда второй аргумент больше остальных.
     */
    @Test
    public void whenSecondBiggerOtherTwo() {
        Max maximum = new Max();
        int result = maximum.max(2, 3, 1);
        assertThat(result, is(3));
    }

    /**
     * Тест метода max с тремя параметрами, когда третий аргумент больше остальных.
     */
    @Test
    public void whenThirdBiggerOtherTwo() {
        Max maximum = new Max();
        int result = maximum.max(2, 1, 3);
        assertThat(result, is(3));
    }

    /**
     * Тест метода max с тремя параметрами, когда аргументы равны.
     */
    @Test
    public void whenAllThreeEqual() {
        Max maximum = new Max();
        int result = maximum.max(4, 4, 4);
        assertThat(result, is(4));
    }
}
