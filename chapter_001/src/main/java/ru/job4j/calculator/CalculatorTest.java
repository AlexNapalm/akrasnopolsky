package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Calculator.
 */
public class CalculatorTest {
    /**
     * Тестовый метод для сложения.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Тестовый метод для вычитания.
     */
    @Test
    public void whenSubtractThreeMinusTwoThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(3D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    /**
     * Тестовый метод для деления.
     */
    @Test
    public void whenDivideEightByFourThenTwo() {
        Calculator calc = new Calculator();
        calc.div(8D, 4D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Тестовый метод для умножения.
     */
    @Test
    public void wheMultipleTwoByThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}