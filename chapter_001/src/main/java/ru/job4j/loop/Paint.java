package ru.job4j.loop;

/**
 * Класс, содержащий метод для построения пирамиды в псевдографике.
 * 4.4. Построить пирамиду в псевдографике. [#4412]
 */
public class Paint {
    /**
     * Метод для построения пирамиды в псевдографике.
     * @param height высота пирамиды.
     * @return строка, содержащая пирамиду в псевдографике.
     */
    public String pyramid(int height) {
        int width = height * 2 - 1;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            int widthLevel = i * 2 + 1;
            int left = (width - widthLevel) / 2;
            int right = width - left;
            for (int j = 0; j < width; j++) {
                builder.append((j >= left && j < right) ? "^" : " ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
