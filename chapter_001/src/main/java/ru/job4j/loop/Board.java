package ru.job4j.loop;

/**
 * Класс, содержащий метод для построения шахматной доски.
 * 4.3. Построить шахматную доску в псевдографике. [#13559]
 */
public class Board {
    /**
     * Метод для построения шахматной доски в псевдографике.
     * @param width ширина доски.
     * @param height высота доски.
     * @return строка символов Х и пробелов в виде шахматной доски.
     */
    public static String paint(int width, int height) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append((j + i) % 2 == 0 ? "x" : " ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
