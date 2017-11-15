package ru.job4j.bankaccounting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User passport.
     */
    private int passport;
    /**
     * Constructs user and adds empty list of accounts.
     * @param name name.
     * @param passport passport.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }
    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Passport getter.
     * @return passport.
     */
    public int getPassport() {
        return this.passport;
    }
    /**
     * Sets name.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets passport.
     * @param passport passport.
     */
    public void setPassport(int passport) {
        this.passport = passport;
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
        return passport == user.passport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", passport=").append(passport);
        sb.append('}');
        return sb.toString();
    }
}
