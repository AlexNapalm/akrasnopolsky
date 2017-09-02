package ru.job4j.professions;

/**
 * Класс описывает некое задание.
 */
public class Task {
    /**
     * Тип задания.
     */
    private String type;

    /**
     * Основной конструктор.
     * @param type тип.
     */
    public Task(String type) {
        this.type = type;
    }

    /**
     * Геттер для типа задания.
     * @return тип задания.
     */
    public String getType() {
        return this.type;
    }
}
