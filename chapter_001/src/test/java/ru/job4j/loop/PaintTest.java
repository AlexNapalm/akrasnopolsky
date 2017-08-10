package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Paint.
 */
public class PaintTest {
    /**
     * Тест метода pyramid(), входной параметр равен 2.
     */
    @Test
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(2);
        String expected = String.format(" ^ %s^^^%s", System.getProperty("line.separator"), System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }

    /**
     * /**
     * Тест метода pyramid(), входной параметр равен 3.
     */
    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
        Paint paint = new Paint();
        String line = System.getProperty("line.separator");
        String result = paint.pyramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line);
        assertThat(result, is(expected));
    }
}
