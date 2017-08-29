package ru.job4j.Professions;

public class Human {
    private String name;
    private int age;
    private String address;
    private String nationality;

    public Human() {
    }

    public Human(String name) {
        this.name = name;
    }

    public Human(String name, int age, String address, String nationality) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.nationality = nationality;
    }

    public String getName() {
        return this.name;
    }

    public void eat (Food food) {
        // some implementation
    }

    public void sleep() {
        //some implementation
    }
}
