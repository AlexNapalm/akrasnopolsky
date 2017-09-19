package ru.job4j.tracker;

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
     * Массив возможных действий для пользователя.
     */
    private UserAction[] actions = new UserAction[6];

    /**
     * Порядковый номер пункта меню в массиве.
     */
    private int position = 0;

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
        this.actions[position++] = new AddItem();
        this.actions[position++] = new ShowItems();
        this.actions[position++] = new EditItem();
        this.actions[position++] = new DeleteItem();
        this.actions[position++] = new FindItemById();
        this.actions[position++] = new FindItemByName();
    }

    /**
     * Добавление нового действия в массив действий.
     * @param action действие.
     */
    public void addAction(UserAction action) {
        this.actions[position++] = action;
    }

    /**
     * Геттер для получения диапазона, допустимого к выбору.
     * @return массив, ширина которого равна допустимому диапазону для выбора.
     */
    public int[] getRange() {
        int[] range = new int[this.actions.length];
        for (int i = 0; i < this.actions.length; i++) {
            range[i] = i;
        }
        return range;
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
