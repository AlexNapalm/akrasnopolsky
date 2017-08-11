package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса RotateArray.
 */
public class RotateArrayTest {
    /**
     * Тест метода rotate с массивом 2х2.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] values = new int[][]{
                {1, 2},
                {3, 4}
        };
        int[][] result = rotateArray.rotate(values);
        int[][] expected = new int[][]{
                {3, 1},
                {4, 2}
        };
        assertThat(result, is(expected));
    }
    /**
     * Тест метода rotate с массивом 3х3.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] values = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] result = rotateArray.rotate(values);
        int[][] expected = new int[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        assertThat(result, is(expected));
    }
}
