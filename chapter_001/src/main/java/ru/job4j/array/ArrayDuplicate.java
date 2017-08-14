package ru.job4j.array;

import java.util.Arrays;

/**
 * Класс, содержащий метод remove, удлаляющий дубликаты из массива.
 * 5.3. Удаление дубликатов в массиве. [#225]
 */
public class ArrayDuplicate {
    /**
     * Метод, Удаляющий дубликаты из массива.
     * @param array массив строк.
     * @return массив строк без дубликатов.
     */
    public String[] remove(String[] array) {
        int count = array.length;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (array[i].equals(array[j])) {
                    for (int k = j; k < array.length - 1; k++) {
                        array[k] = array[k + 1];
                    }
                    count--;
                }
            }
        }
        return Arrays.copyOf(array, count);
    }
}
