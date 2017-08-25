package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для теста метода combine.
 */
public class MergeTest {
    /**
     * Тест класса combine.
     */
    @Test
    public void twoSortedArraysMergedInOneSortedArray() {
        Merge merge = new Merge();
        int[] arr1 = {1, 4, 6, 8, 10, 22, 25, 31};
        int[] arr2 = {2, 5, 7, 12, 16, 19, 21, 25, 26};
        int[] result = merge.combineArrays(arr1, arr2);
        int[] expected = {1, 2, 4, 5, 6, 7, 8, 10, 12, 16, 19, 21, 22, 25, 25, 26, 31};

        assertThat(result, is(expected));
    }
    /**
     * Тест класса combine.
     */
    @Test
    public void twoEqualArraysMergedInOneArray() {
        Merge merge = new Merge();
        int[] arr1 = {1, 1, 1, 1, 1};
        int[] arr2 = {1, 1, 1, 1, 1};
        int[] result = merge.combineArrays(arr1, arr2);
        int[] expected = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        assertThat(result, is(expected));
    }
}
