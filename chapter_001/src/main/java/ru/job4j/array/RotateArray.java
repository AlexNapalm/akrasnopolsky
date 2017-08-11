package ru.job4j.array;

/**
 * Класс для метода rotate.
 * 5.2. Создание программы поворота квадратного массива. [#223].
 */
public class RotateArray {
    /**
     * Метод, переворачивающий массив на 90 градусов.
     * @param array массив.
     * @return массив, перевернутый на 90 градусов.
     */
    public int[][] rotate(int[][] array) {
        int[][] tempArray = new int[array.length][array.length];

        for (int indexA = 0; indexA < array.length; indexA++) {
            for (int indexB = 0; indexB < array.length; indexB++) {
                tempArray[indexB][array.length - indexA - 1] = array[indexA][indexB];
            }
        }
        array = tempArray;
        return array;
    }
}
