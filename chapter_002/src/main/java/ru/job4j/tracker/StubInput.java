package ru.job4j.tracker;

/**
 * Класс, эмулирующий действия пользователя.
 */
public class StubInput implements Input {
    /**
     * Массив пользовательских ответов.
     */
    private String[] answers;
    /**
     * Счетчик элементов массива.
     */
    private int position = 0;

    /**
     * Конструктор.
     * @param answers массив ответов.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Метод для ввода строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ с клавиатуры.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        return this.answers[position++];
    }

    /**
     * Метод для ввода строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ с клавиатуры.
     * @param range массив допустим значений, которые допускаются к вводу.
     * @return ответ пользователя.
     */
    public int ask(String question, int[] range) {
        //throw new UnsupportedOperationException("Unsupported operation");
        return 0;
    }
}
