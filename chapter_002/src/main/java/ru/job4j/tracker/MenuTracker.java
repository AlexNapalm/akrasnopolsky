package ru.job4j.tracker;

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
        String id = input.ask("Enter the task's id: ");
        String name = input.ask("Enter the task's name: ");
        String description = input.ask("Enter the task's desc: ");
        Item item = new Item(name, description, 123L);
        item.setId(id);
        tracker.update(item);
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
        actions.add(new FindItemByName());
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
            String name = input.ask("Enter the task's name: ");
            String description = input.ask("Enter the task's desc: ");
            tracker.add(new Item(name, description, 123L));
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
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("%s | %s | %s", item.getId(), item.getName(), item.getDescription()));
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
            String id = input.ask("Enter the task's id: ");
            tracker.delete(tracker.findById(id));
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
            String id = input.ask("Enter the task's id: ");
            System.out.println(String.format("%s | %s | %s", tracker.findById(id).getId(),
                                        tracker.findById(id).getName(), tracker.findById(id).getDescription()));
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
    private class FindItemByName extends BaseAction {

        /**
         * Конструктор.
         */
        FindItemByName() {
            super(5, "Find item by name.");
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
            for (Item item : tracker.findByName(searchKey)) {
                System.out.println(String.format("%s | %s | %s", item.getId(), item.getName(), item.getDescription()));
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
