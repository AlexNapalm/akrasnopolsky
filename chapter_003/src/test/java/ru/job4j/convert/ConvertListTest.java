package ru.job4j.convert;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование класса ConvertList.
 */
public class ConvertListTest {
    /**
     * Тест метода toList.
     */
    @Test
    public void whenTwoDimensionArrayConvertToList() {
        int[][] testArray = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        ConvertList cl = new ConvertList();
        List<Integer> result = cl.toList(testArray);
        assertThat(result, is(expected));
    }

    /**
     * Тест метода toArray.
     */
    @Test
    public void whenListConvertToArray() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.add(6);
        testList.add(7);
        int[][] expected = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 0, 0}};
        ConvertList cl = new ConvertList();
        int[][] result = cl.toArray(testList, 3);
        assertThat(result, is(expected));
    }

}
