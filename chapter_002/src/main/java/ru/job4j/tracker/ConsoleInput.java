package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Класс реализует консольный ввод с клавиатуры.
 */
public class ConsoleInput implements Input {

    /**
     * Метод для ввода строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ.
     * @return объект scanner для ввода с клавиатуры.
     */
    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Метод для ввода числа с клавиатуры.
     * @return объект scanner для ввода с клавиатуры.
     */
    public int enterNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
