package ru.job4j.Professions;

public class Teacher extends Human {
    private boolean stricktness;
    private String specialization;

    public Teacher(String name, boolean stricktness, String specialization) {
        super(name);
        this.stricktness = stricktness;
        this.specialization = specialization;
    }

    public void praise(Student student) {
        System.out.println("Teacher " + this.getName() + " praises student " + student.getName());
    }

    public void scold(Student student) {
        System.out.println("Teacher " + this.getName() + " scolds student " + student.getName());
    }

    public void callToBlackboard(Student student) {
        System.out.println("Teacher " + this.getName() + " calls student " + student.getName() + " to blackboard");
    }

    public void check(Task task) {
        System.out.println("Teacher " + this.getName() + " checks " + task.getType() + " task");
    }
}
