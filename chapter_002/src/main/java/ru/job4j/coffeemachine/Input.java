package ru.job4j.coffeemachine;

/**
 * Интерфейс консольного ввода с клавиатуры.
 */
public interface Input {
    /**
     * Ввод строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ с клавиатуры.
     * @return объект scanner для ввода с клавиатуры.
     */
    int ask(String question);
}
