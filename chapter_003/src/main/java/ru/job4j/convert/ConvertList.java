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
        int columns;
        if (list.size() % rows == 0) {
            columns = list.size() / rows;
        } else {
            columns = list.size() / rows + 1;
        }
        int[][] result = new int[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (list.size() > count) {
                    result[i][j] = list.get(count++);
                }
            }
        }
        return result;
    }
}
