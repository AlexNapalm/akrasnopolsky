package ru.job4j.bankaccounting;

import java.util.Objects;

public class Account {
    /**
     * Account value.
     */
    private double value;
    /**
     * Account requisites.
     */
    private int requisites;
    /**
     * Constructs account.
     * @param requisites requisites.
     */
    public Account(int requisites) {
        this.value = 0;
        this.requisites = requisites;
    }

    /**
     * Value getter.
     * @return value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Value setter.
     * @param value value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Requisites getter.
     * @return
     */
    public int getRequisites() {
        return this.requisites;
    }

    /**
     * Requisites setter.
     * @param requisites requisites.
     */
    public void setRequisites(int requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return requisites == account.requisites;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("requisites=").append(requisites);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
