package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования.
 */
public class PaintTest {
    /**
     * Тест прорисовки квадрата.
     */
    @Test
    public void whenDrawSquareThenOutSquare() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(result));
        new Paint().draw(new Square());
        assertThat(result.toString(), is(String.format("XXX%1$sXXX%1$sXXX%1$s%1$s", System.lineSeparator())));
    }
    /**
     * Тест прорисовки треугольника.
     */
    @Test
    public void whenDrawTriangleThenOutTriangle() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        System.setOut(new PrintStream(result));
        new Paint().draw(new Triangle());
        assertThat(result.toString(), is(String.format("X%1$sXX%1$sXXX%1$s%1$s", System.lineSeparator())));
    }
}
