package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Counter.
 */
public class CounterTest {
    /**
     * Тестируем метод add класса Counter.
     */
    @Test
    public void fromOneToTenSumThirty() {
        Counter counter = new Counter();
        int expected = 30;
        int actual = counter.add(1, 10);
        assertThat(actual, is(expected));
    }
}
