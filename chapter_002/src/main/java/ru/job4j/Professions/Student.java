package ru.job4j.Professions;

public class Student extends Human {
    private int grade;
    private boolean lazy;

    public Student(String name, int grade, boolean lazy) {
        super(name);
        this.grade = grade;
        this.lazy = lazy;
    }

    public String getName() {
        return super.getName();
    }

    public void learn(Task task) {
        System.out.println("Student " + this.getName() + "learns " + task.getType() + " task");
    }

    public void bully(Student student) {
        System.out.println("Student " + this.getName() + "bullies student " + student.getName());
    }
}
