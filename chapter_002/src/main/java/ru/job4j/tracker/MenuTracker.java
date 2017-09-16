package ru.job4j.tracker;

/**
 * Класс, описывающий редактирование заявки.
 */
class EditItem implements UserAction {

    /**
     * Идентификатор пункта меню.
     * @return Идентификатор пункта меню
     */
    public int key() {
        return 2;
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
        return String.format("%s. %s", this.key(), "Edit item.");
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
     * Массив возможных действий для пользователя.
     */
    private UserAction[] actions = new UserAction[7];


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
     * Заполнение массива возможными действиями.
     */
    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new ExitProgram();
    }

    /**
     * Запуск.
     * @param key номер пункта меню.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
    private class AddItem implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 0;
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
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }


    /**
     * Класс описывает вывод на экран всех заявок в трекере.
     */
    private static class ShowItems implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 1;
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
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    /**
     * Класс описывает удаление заявки.
     */
    private class DeleteItem implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 3;
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
            return String.format("%s. %s", this.key(), "Delete item.");
        }
    }

    /**
     * Класс описывает поиск заявки по id.
     */
    private class FindItemById implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 4;
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
            return String.format("%s. %s", this.key(), "Find item by id.");
        }
    }

    /**
     * Класс описывает поиск заявки по имени.
     */
    private class FindItemByName implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 5;
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
            return String.format("%s. %s", this.key(), "Find item by name.");
        }
    }

    /**
     * Класс описывает выход из программы.
     */
    private class ExitProgram implements UserAction {

        /**
         * Ключ-идентификатор пункта меню.
         * @return номер пункта меню.
         */
        public int key() {
            return 6;
        }

        /**
         * Выполнение.
         * @param input объект реализующий интерфейс Input.
         * @param tracker объект Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            // пустая реализация
        }

        /**
         * Описание пункта меню.
         * @return Описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit program.");
        }
    }
}
