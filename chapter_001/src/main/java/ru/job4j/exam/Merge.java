package ru.job4j.exam;

/**
 * Класс для метода, объединяющего два массива в один отсортированный по возрастанию массив.
 */
public class Merge {
    /**
     * Метод объединяет два массива в один отсортированный по возрастанию массив.
     * @param firstArray первый массив.
     * @param secondArray второй массив.
     * @return объединенный и отсортированный по возрастанию массив.
     */
    public int[] combineArrays(int[] firstArray, int[] secondArray) {
        int resultArrayLength = firstArray.length + secondArray.length;
        int[] resultArray = new int[resultArrayLength];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < resultArrayLength; i++) {
            if (i1 == firstArray.length) {
                System.arraycopy(secondArray, i2, resultArray, i, secondArray.length - i2);
                return resultArray;
            } else if (i2 == secondArray.length) {
                System.arraycopy(firstArray, i1, resultArray, i, firstArray.length - i1);
                return resultArray;
            } else {
                if (firstArray[i1] < secondArray[i2]) {
                    resultArray[i] = firstArray[i1];
                    i1++;
                } else {
                    resultArray[i] = secondArray[i2];
                    i2++;
                }
            }
        }
        return resultArray;
    }
}
