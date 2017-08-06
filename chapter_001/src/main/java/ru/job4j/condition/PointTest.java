package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Point.
 */
public class PointTest {
    /**
     * Тестовый метод для тестирования метода is() из класса Point.
     */
    @Test
    public void belongsToFunction() {
        Point point = new Point(2, 8);
        boolean result = point.is(2, 4);
        assertThat(result, is(true));
    }

    /**
     * Тестовый метод для тестирования метода is() из класса Point.
     */
    @Test
    public void doesntBelongToFunction() {
        Point point = new Point(2, 6);
        boolean result = point.is(2, 4);
        assertThat(result, is(false));
    }
}
