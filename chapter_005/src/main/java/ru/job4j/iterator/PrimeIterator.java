package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    /**
     * Array of values.
     */
    private int[] values;
    /**
     * Counter for index.
     */
    private int position = 0;
    /**
     * Constructs Iterator.
     * @param values array of values.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Calculates, if number is prime.
     * @param value number.
     * @return true, if the number is prime.
     */
    public boolean isPrime(int value) {
        boolean result = true;
        if (value == 1) {
            return false;
        }
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Overriden hasNext.
     * @return true, if there are any prime numbers left.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (position >= this.values.length) {
            return false;
        }
        if (isPrime(this.values[position])) {
            result = true;
        } else {
            for (int i = position; i < this.values.length; i++) {
                if (isPrime(this.values[i])) {
                    result = true;
                    position = i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Overriden next.
     * @return current prime number.
     */
    @Override
    public Object next() {
        if (hasNext()) {
            return this.values[position++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
