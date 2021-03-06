package ru.job4j.tracker;

import java.util.List;
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
     * Метод для ввода строки с клавиатуры.
     * @param question вопрос, на который пользователь должен ввести ответ
     * @param range лист допустим значений, которые допускаются к вводу.
     * @return объект scanner для ввода с клавиатуры.
     */
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else  {
            throw new MenuOutException("Out of menu range");
        }
    }
}
