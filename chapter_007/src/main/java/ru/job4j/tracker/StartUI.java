package ru.job4j.tracker;

import java.util.List;

/**
 * Класс - точка входа в программу.
 */
public class StartUI {

    /**
     * Объект, реализующий интерфейс Input.
     */
    private Input input;

    /**
     * Объект трекер.
     */
    private Tracker tracker;

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
        List<Integer> ranges = menu.getRange();
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select: ", ranges));
        } while (!"y".equals(this.input.ask("Exit? (y): ")));
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController();
        controller.execSqlScript("createtables.sql");
        controller.execSqlScript("insertvalues.sql");
        Tracker tracker = new Tracker(controller);
        Input input = new ValidateInput();
        new StartUI(input, tracker).init();
    }
}
