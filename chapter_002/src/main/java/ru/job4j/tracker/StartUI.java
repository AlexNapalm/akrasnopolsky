package ru.job4j.tracker;

/**
 * Класс - точка входа в программу.
 */
public class StartUI {
    /**
     * Пункт меню 0. Add new Item.
     */
    private static final String ADD = "0";
    /**
     * Пункт меню 1. Show all items.
     */
    private static final String SHOW_ALL = "1";
    /**
     * Пункт меню 2. Edit item.
     */
    private static final String EDIT_ITEM = "2";
    /**
     * Пункт меню 3. Delete item.
     */
    private static final String DELETE_ITEM = "3";
    /**
     * Пункт меню 4. Find item by Id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Пункт меню 5. Find items by name.
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Пункт меню 6. Exit Program.
     */
    private static final String EXIT = "6";
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
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Инициализация меню.
     */
    public void init() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        String userChoice = input.ask("Select: ");

        if (userChoice.equals(ADD)) {
            addItem();
        } else if (userChoice.equals(SHOW_ALL)) {
            showAll();
        } else if (userChoice.equals(EDIT_ITEM)) {
            editItem();
        } else if (userChoice.equals(DELETE_ITEM)) {
            deleteItem();
        } else if (userChoice.equals(FIND_BY_ID)) {
            findById();
        } else if (userChoice.equals(FIND_BY_NAME)) {
            findByName();
        } else if (userChoice.equals(EXIT)) {
            System.exit(0);
        } else {
            System.out.println("Error. Try again.");
        }
        System.out.println(" ");
        init();
    }

    /**
     * Добавление заявки.
     */
    private void addItem() {
        String name = input.ask("Enter the task's name: ");
        String description = input.ask("Enter the task's description: ");
        tracker.add(new Item(name, description, 123L));
    }

    /**
     * Показать все заявки.
     */
    private void showAll() {
        for (Item item : tracker.findAll()) {
            System.out.println(item.getId() + " | " + item.getName() + " | " + item.getDescription());
        }
    }

    /**
     * Редактирование заявки.
     */
    private void editItem() {
        String itemID = input.ask("Enter id: ");
        String newName = input.ask("Enter new task's name: ");
        String newDescription = input.ask("Enter new task's description: ");
        // Создаем новую заявку с обновленными именем и описанием.
        Item updatedItem = new Item(newName, newDescription, 123L);
        // Проставляем ей id старой заявки.
        updatedItem.setId(itemID);
        // Заменяем заявку в трекере, id при этом остается прежний.
        tracker.update(updatedItem);
    }

    /**
     * Удаление заявки.
     */
    private void deleteItem() {
        String itemID = input.ask("Enter id: ");
        tracker.delete(tracker.findById(itemID));
    }

    /**
     * Поиск заявки по id.
     */
    private void findById() {
        String itemID = input.ask("Enter id: ");
        Item searchResult = tracker.findById(itemID);
        System.out.println(searchResult.getName() + " | " + searchResult.getName() + " | " + searchResult.getDescription());
    }

    /**
     * Поиск заявок по имени.
     */
    private void findByName() {
        // Строка, по которой осуществляем поиск.
        String searchKey = input.ask("Enter search key: ");
        for (Item item : tracker.findByName(searchKey)) {
            System.out.println(item.getId() + " | " + item.getName() + " | " + item.getDescription());
        }
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"0", "hello", "world", "6"});
        new StartUI(input, tracker).init();
    }
}
