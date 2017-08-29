package ru.job4j.Professions;

public class Doctor extends Human {
    private String grade;
    private boolean rudeness;

    public Doctor(String name, String grade, boolean rudeness) {
        super(name);
        this.grade = grade;
        this.rudeness = rudeness;
    }

    public void inspect(Human human) {
        System.out.println("Doctor " + this.getName() + "inspects pacient" + human.getName());
    }

    public void analyze(Specimen specimen) {
        System.out.println("Doctor " + this.getName() + "analyzes " + specimen);
    }

    public void makeSurgery(Human human) {
        System.out.println("Doctor " + this.getName() + "makes surgery to " + human.getName());
    }
}
