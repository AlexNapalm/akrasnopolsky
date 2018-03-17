package ru.job4j.tracker;

/**
 * Класс, описывающий заявку.
 */
public class Item {
    /**
     * Id заявки.
     */
    private int id;

    /**
     * Заголовок заявки.
     */
    private String title;

    /**
     * Описание заявки.
     */
    private String description;

    /**
     * Дата создания.
     */
    private long create;

    /**
     * Дефолтный конструктор.
     */
    public Item() {
    }

    /**
     * Основной конструктор.
     * @param name имя.
     * @param description описание.
     * @param create дата создания.
     */
    public Item(String name, String description, long create) {
        this.title = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Геттер для описания.
     * @return описание.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Геттер для даты создания.
     * @return дата создания.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Геттер для id.
     * @return id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Сеттер для id.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }
}
