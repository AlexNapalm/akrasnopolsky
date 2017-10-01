package ru.job4j.coffeemachine;

import java.util.Arrays;

/**
 * Класс, где происходит запуск "кофейного автомата".
 */
public class StartProgram {
    /**
     * Объект CoffeeMachine.
     */
    private CoffeeMachine coffeeMachine;
    /**
     * Объект Input.
     */
    private Input input;

    /**
     * Конструктор.
     * @param coffeeMachine объект CoffeeMachine.
     * @param input объект Input.
     */
    public StartProgram(CoffeeMachine coffeeMachine, Input input) {
        this.coffeeMachine = coffeeMachine;
        this.input = input;
    }

    /**
     * Инициализация программы.
     */
    public void init() {
        int price = input.ask("Enter the price: ");
        int pay = input.ask("Enter the pay: ");
        System.out.println("The change is: " + Arrays.toString(coffeeMachine.giveChange(price, pay)));
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Input input = new ConsoleInput();
        new StartProgram(coffeeMachine, input).init();
    }
}
