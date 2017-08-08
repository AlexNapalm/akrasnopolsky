package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Factorial.
 */
public class FactorialTest {
    /**
     * Тест для метода calc, входное значение 5.
     */
    @Test
    public void whenInFiveFactorialOneHundredTwenty() {
        Factorial factorial = new Factorial();
        int actual = factorial.calc(5);
        assertThat(actual, is(120));
    }

    /**
     * Тест для метода calc, входное значение 0.
     */
    @Test
    public void whenInZeroFactorialOne() {
        Factorial factorial = new Factorial();
        int actual = factorial.calc(0);
        assertThat(actual, is(1));
    }
}
