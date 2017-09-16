package ru.job4j.tracker;

/**
 * Класс - точка входа в программу.
 */
public class StartUI {

    /**
     * Значение для выхода из программы.
     */
    private static final int EXIT = 6;
    /**
     * Объект, реализующий интерфейс Input.
     */
    private Input input;
    /**
     * Объект трекер.
     */
    private Tracker tracker = new Tracker();

    /**
     * Конструктор.
     * @param input объект, реализующий интерфейс Input.
     * @param tracker объект tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Инициализация меню.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int key;
        menu.fillActions();
        do {
            menu.show();
            key = Integer.valueOf(input.ask("Select: "));
            menu.select(key);
        } while (key != EXIT);
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(input, tracker).init();
    }
}
