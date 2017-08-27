package ru.job4j.exam;

/**
 * Класс для метода contains, проверяющего, содержит ли строка заданную подстроку.
 * Проверка, что одно слово находится в другом слове [#228].
 */
public class Substring {
    /**
     * Метод проверяет, содержит ли строка заданную подстроку.
     * @param origin строка.
     * @param sub подстрока.
     * @return true - если содержит, false - если нет.
     */
    public boolean contains(String origin, String sub) {
        char[] originCharArray = origin.toCharArray();
        char[] subCharArray = sub.toCharArray();
        boolean result = false;

        for (int i = 0; i < originCharArray.length; i++) {
            if (originCharArray[i] == subCharArray[0]) {
                for (int j = 1; j < subCharArray.length; j++) {
                    result = subCharArray[j] == originCharArray[i + j];
                    if (!result) {
                        return result;
                    }
                    if (j == subCharArray.length - 1 && result) {
                        return result;
                    }
                }
            }
        }
        return result;
    }
}
