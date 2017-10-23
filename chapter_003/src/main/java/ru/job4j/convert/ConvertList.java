package ru.job4j.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для реализации конвертация массива в список и обратно.
 */
public class ConvertList {
    /**
     * Конвертация массива в список.
     * @param array двухмерный массив.
     * @return список, полученный из массива.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                result.add(array[i][j]);
            }
        }
        return result;
    }

    /**
     * Конвертация списка в двухмерный массив.
     * @param list список.
     * @param rows количество строк в массиве.
     * @return двухмерный массив, полученный из списка.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        List<Integer> listWithoutNulls = new ArrayList<>();
        for (Integer element : list) {
            if (element != null) {
                listWithoutNulls.add(element);
            }
        }

        int columns;
        if (listWithoutNulls.size() % rows == 0) {
            columns = listWithoutNulls.size() / rows;
        } else {
            columns = listWithoutNulls.size() / rows + 1;
        }
        int[][] result = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (listWithoutNulls.size() > count) {
                    result[i][j] = listWithoutNulls.get(count++);
                }
            }
        }
        return result;
    }

    /**
     * Конвертация списка массивов в список элементов этих массивов.
     * @param list список, содержащий массивы целых чисел.
     * @return список целых чисел.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int i : array) {
                result.add(i);
            }
        }
        return result;
    }
}
