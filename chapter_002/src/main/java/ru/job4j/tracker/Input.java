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

    /**
     * Ввод строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ с клавиатуры.
     * @param range массив допустим значений, которые допускаются к вводу.
     * @return объект scanner для ввода с клавиатуры.
     * @throws MenuOutException исключение выхода за пределы допустимых значений.
     */
    int ask(String question, int[] range) throws MenuOutException;
}
