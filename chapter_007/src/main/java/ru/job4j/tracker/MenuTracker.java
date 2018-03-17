package ru.job4j.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий редактирование заявки.
 */
class EditItem extends BaseAction {

    /**
     * Основной конструктор.
     */
    EditItem() {
        super(2, "Edit item.");
    }

    /**
     * Идентификатор пункта меню.
     * @return Идентификатор пункта меню
     */
    public int key() {
        return this.getKey();
    }

    /**
     * Реализация редактирования заявки.
     * @param input объект реализующий интерфейс Input.
     * @param tracker объект Tracker.
     */
    public void execute(Input input, Tracker tracker) {
        int id = Integer.parseInt(input.ask("Enter the task's id: "));
        String title = input.ask("Enter the task's title: ");
        String description = input.ask("Enter the task's desc: ");
        tracker.update(id, title, description);
    }

    /**
     * Описание пункта меню.
     * @return Описание пункта меню.
     */
    public String info() {
        return String.format("%s. %s", this.getKey(), this.getName());
    }
}

/**
 * Класс опсиывает меню для трекера.
 */
public class MenuTracker {

    /**
     * Объект, реализующий интерфейс Input.
     */
    private Input input;

    /**
     * Объект Tracker.
     */
    private Tracker tracker;

    /**
     * Лист возможных действий для пользователя.
     */
    private List<UserAction> actions = new ArrayList<>(6);

    /**
     * Основной конструктор.
     * @param input объект, реализующий интерфейс Input.
     * @param tracker объект Trcker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Заполнение листа возможными действиями.
     */
    public void fillActions() {
        actions.add(new AddItem());
        actions.add(new ShowItems());
        actions.add(new EditItem());
        actions.add(new DeleteItem());
        actions.add(new FindItemById());
        actions.add(new FindItemByTitle());
    }

    /**
     * Геттер для получения диапазона, допустимого к выбору.
     * @return лист, ширина которого равна допустимому диапазону для выбора.
     */
    public List<Integer> getRange() {
        List<Integer> range = new ArrayList<>(this.actions.size());
        for (int i = 0; i < 6; i++) {
            range.add(i);
        }
        return range;
    }

    /**
     * Запуск.
     * @param key номер пункта меню.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Вывод меню на экран.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Класс описывает добавление заявки.
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор.
         */
        AddItem() {
            super(0, "Add the new item.");
        }

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return this.getKey();
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String title = input.ask("Enter the task's title: ");
            String description = input.ask("Enter the task's desc: ");
            tracker.add(title, description);
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.getKey(), this.getName());
        }
    }


    /**
     * Класс описывает вывод на экран всех заявок в трекере.
     */
    private static class ShowItems extends BaseAction {

        /**
         * Конструктор.
         */
        ShowItems() {
            super(1, "Show all items.");
        }

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return this.getKey();
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            ResultSet result = tracker.findAll();
            try {
                while (result.next()) {
                    System.out.println(String.format("%s | %s | %s", result.getInt("id"),
                            result.getString("title"), result.getString("description")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.getKey(), this.getName());
        }
    }

    /**
     * Класс описывает удаление заявки.
     */
    private class DeleteItem extends BaseAction {

        /**
         * Конструктор.
         */
        DeleteItem() {
            super(3, "Delete item.");
        }

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return this.getKey();
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            int id = Integer.parseInt(input.ask("Enter the task's id: "));
            tracker.delete(id);
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.getKey(), this.getName());
        }
    }

    /**
     * Класс описывает поиск заявки по id.
     */
    private class FindItemById extends BaseAction {

        /**
         * Конструктор.
         */
        FindItemById() {
            super(4, "Find item by id.");
        }

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return this.getKey();
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            int id = Integer.parseInt(input.ask("Enter the task's id: "));
            ResultSet result = tracker.findById(id);
            try {
                while (result.next()) {
                    System.out.println(String.format("%s | %s | %s", result.getInt("id"),
                                        result.getString("title"), result.getString("description")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.getKey(), this.getName());
        }
    }

    /**
     * Класс описывает поиск заявки по имени.
     */
    private class FindItemByTitle extends BaseAction {

        /**
         * Конструктор.
         */
        FindItemByTitle() {
            super(5, "Find item by title.");
        }

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return this.getKey();
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String searchKey = input.ask("Enter the task's name: ");
            ResultSet result = tracker.findByTitle(searchKey);
            try {
                while (result.next()) {
                    System.out.println(String.format("%s | %s | %s", result.getString("id"),
                                                                    result.getString("title"),
                                                                    result.getString("description")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.getKey(), this.getName());
        }
    }
}
