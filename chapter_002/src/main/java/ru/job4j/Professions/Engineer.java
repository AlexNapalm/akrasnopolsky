package ru.job4j.Professions;

public class Engineer extends Human {
    private String favoriteSoft;
    private String license;

    public Engineer(String name, String favoriteSoft, String license) {
        super(name);
        this.favoriteSoft = favoriteSoft;
        this.license = license;
    }

    public void assemble (Part part1, Part part2, Part part3) {
        System.out.println("Engineer " + this.getName() + " assembles " + part1 + part2 + part3);
    }

    public void draw (Task task) {
        System.out.println("Engineer " + this.getName() + " draws blueprint for " + task.getType());
    }
}
