package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса ArrayDuplicate.
 */
public class ArrayDuplicateTest {
    /**
     * Тест для метода remove(), на входе массив из 5 элементов, 2 из которых дубликаты.
     */
    @Test
    public void whenFiveElementsAndTwoDuplicatesThenThreeElementsArray() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] values = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expected = {"Привет", "Мир", "Супер"};
        String[] result = arrayDuplicate.remove(values);

        assertThat(result, is(expected));
    }
    /**
     * Тест для метода remove(), на входе массив из 5 элементов, без дубликатов.
     */
    @Test
    public void whenFiveElementsAndNoDuplicatesThenFiveElementsArray() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] values = {"Привет", "Мир", "Жвачка", "Супер", "Космос"};
        String[] expected = {"Привет", "Мир", "Жвачка", "Супер", "Космос"};
        String[] result = arrayDuplicate.remove(values);

        assertThat(result, is(expected));
    }
}
