package ru.job4j.tracker;

/**
 * Класс, реализующий проверку корректности введенных пользователем данных.
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Метод для ввода данных с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ
     * @param range массив допустим значений, которые допускаются к вводу.
     * @return Значение, введенное пользователем, либо исключения, сообщающие о неверности ввода.
     */
    public int ask(String question, int[] range) {
        int value = 0;
        boolean invalid = true;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter valid data again");
            }
        } while (invalid);
        return value;
    }
}
