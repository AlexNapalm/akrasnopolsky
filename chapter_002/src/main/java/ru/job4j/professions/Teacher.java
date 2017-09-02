package ru.job4j.professions;

/**
 * Класс описывает преподавателя.
 */
public class Teacher extends Human {
    /**
     * Строгость преподавателя.
     */
    private boolean stricktness;

    /**
     * Специализация препоадавателя.
     */
    private String specialization;

    /**
     * Основной конструктор.
     * @param name имя.
     * @param stricktness строгость.
     * @param specialization специализация.
     */
    public Teacher(String name, boolean stricktness, String specialization) {
        super(name);
        this.stricktness = stricktness;
        this.specialization = specialization;
    }

    /**
     * Похвалить студента.
     * @param student студент.
     */
    public void praise(Student student) {
        System.out.println("Teacher " + this.getName() + " praises student " + student.getName());
    }

    /**
     * Отругать студента.
     * @param student студент.
     */
    public void scold(Student student) {
        System.out.println("Teacher " + this.getName() + " scolds student " + student.getName());
    }

    /**
     * Вызвать студента к доске.
     * @param student студент.
     */
    public void callToBlackboard(Student student) {
        System.out.println("Teacher " + this.getName() + " calls student " + student.getName() + " to blackboard");
    }

    /**
     * Проверить задание.
     * @param task задание.
     */
    public void check(Task task) {
        System.out.println("Teacher " + this.getName() + " checks " + task.getType() + " task");
    }
}
