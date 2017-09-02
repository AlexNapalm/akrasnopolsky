package ru.job4j.tracker;

/**
 * Класс, описывающий заявку.
 */
public class Item {
    /**
     * Id заявки.
     */
    private String id;

    /**
     * имя заявителя.
     */
    private String name;

    /**
     * описание заявки.
     */
    private String description;

    /**
     * дата создания.
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
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
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
    public String getId() {
        return this.id;
    }

    /**
     * Сеттер для id.
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
