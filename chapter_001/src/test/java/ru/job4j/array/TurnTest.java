package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса Turn.
 */
public class TurnTest {
    /**
     * Тест метода back() c массивом с четным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] values = {1, 2, 3, 4, 5, 6};
        int[] result = turn.back(values);
        int[] expected = {6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода back() c массивом с нечетным количеством элементов.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = turn.back(values);
        int[] expected = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
