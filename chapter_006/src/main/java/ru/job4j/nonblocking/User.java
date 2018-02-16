package ru.job4j.nonblocking;

import java.util.Objects;

public class User {

    private String name;
    private int id;
    private volatile int version;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.version++;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && version == user.version
                && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, version);
    }
}
