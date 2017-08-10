package ru.job4j.array;

/**
 * Класс для создания метода, который переворачивает массив.
 * 5.0 Перевернуть массив [#4441]
 */
public class Turn {
    /**
     * Метод, который переворачивает массив {1, 2, 3} => {3, 2, 1}.
     * @param array массив.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        int halfBorder = array.length % 2 == 0 ? (array.length / 2 - 1) : (array.length / 2);
        int temp;
        for (int i = 0; i <= halfBorder; i++) {
            temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
