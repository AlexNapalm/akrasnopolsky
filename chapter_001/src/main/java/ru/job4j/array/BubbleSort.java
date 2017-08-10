package ru.job4j.array;

/**
 * Класс для метода пузырьковой сортировки.
 * 5.1. Создать программу для сортировки массива методом перестановки. [#195].
 */
public class BubbleSort {
    /**
     * Метод, сортирующий массив пузырьковым способом.
     * @param array массив.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }
}
