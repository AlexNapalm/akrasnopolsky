package ru.job4j.professions;

/**
 * Класс описывает студента.
 */
public class Student extends Human {
    /**
     * Класс или группа студента.
     */
    private int grade;

    /**
     * Ленивость студента.
     */
    private boolean lazy;

    /**
     * Основной конструктор.
     * @param name имя.
     * @param grade класс.
     * @param lazy ленивость.
     */
    public Student(String name, int grade, boolean lazy) {
        super(name);
        this.grade = grade;
        this.lazy = lazy;
    }

    /**
     * Геттер для имени.
     * @return имя.
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Выучить задание.
     * @param task задание.
     */
    public void learn(Task task) {
        System.out.println("Student " + this.getName() + "learns " + task.getType() + " task");
    }

    /**
     * Начать задирать другого студента.
     * @param student студент.
     */
    public void bully(Student student) {
        System.out.println("Student " + this.getName() + "bullies student " + student.getName());
    }
}
