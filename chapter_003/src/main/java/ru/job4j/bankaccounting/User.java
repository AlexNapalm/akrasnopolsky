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
     * User accounts.
     */
    private List<Account> userAccounts;
    /**
     * Constructs user and adds empty list of accounts.
     * @param name name.
     * @param passport passport.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
        this.userAccounts = new ArrayList<>();
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
    /**
     * User accounts getter.
     * @return list of user accounts.
     */
    public List<Account> getUserAccounts() {
        return this.userAccounts;
    }
    /**
     * Sets list of user accounts.
     * @param userAccounts list of user accounts.
     */
    public void setUserAccounts(List<Account> userAccounts) {
        this.userAccounts = userAccounts;
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
