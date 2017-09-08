package ru.job4j.tracker;

/**
 * Интерфейс консольного ввода с клавиатуры.
 */
public interface Input {
    /**
     * Ввод строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ с клавиатуры.
     * @return объект scanner для ввода с клавиатуры.
     */
    String ask(String question);
}
