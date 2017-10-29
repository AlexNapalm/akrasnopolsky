package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Arrays;
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
        expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
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
        testList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int[][] expected = {{1, 2, 3},
                            {4, 5, 6},
                            {7, 0, 0}};
        ConvertList cl = new ConvertList();
        int[][] result = cl.toArray(testList, 3);
        assertThat(result, is(expected));
    }

    /**
     * Тест метода toArray.
     */
    @Test
    public void whenListHasNullConvertToArray() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(null);
        testList.add(4);
        testList.add(5);
        int[][] expected = {{1, 2},
                            {4, 5}};
        ConvertList cl = new ConvertList();
        int[][] result = cl.toArray(testList, 2);
        assertThat(result, is(expected));
    }

    /**
     * Тест метода convert.
     */
    @Test
    public void whenListOfArraysConvertToListOfIntegers() {
        List<int[]> listOfArrays = new ArrayList<>();
        listOfArrays.add(new int[]{1, 2});
        listOfArrays.add(new int[]{3, 4, 5, 6});
        ConvertList cl = new ConvertList();
        List<Integer> result = cl.convert(listOfArrays);
        List<Integer> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(result, is(expected));
    }
}
