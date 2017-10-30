package ru.job4j.sort;

/**
 * Класс User.
 */
public class User implements Comparable<User> {
    /**
     * Name.
     */
    private String name;
    /**
     * Age.
     */
    private int age;
    /**
     * Constructor.
     * @param name name.
     * @param age age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter for name.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for age.
     * @return age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter for name.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for age.
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Implementation of compareTo, comparing age of user.
     * @param user user to be compared to.
     * @return 1, this.age bigger than user.age, 0 if ages are equals, -1 if user.age is bigger.
     */
    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.age);
    }
    /**
     * Overriden toString.
     * @return user information.
     */
    @Override
    public String toString() {
        return String.format("User{name='%s', age='%d'}", this.name, this.age);
    }
}
