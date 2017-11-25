package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    /**
     * Array of values.
     */
    private int[] values;
    /**
     * Count for index.
     */
    private int position;

    /**
     * Constructs iterator.
     * @param values array.
     */
    public EvenIterator(int[] values) {
        this.values = values;
        this.position = calculatePosition(values);
    }

    /**
     * Calculates the first even element index.
     * @param values array.
     * @return index.
     */
    public int calculatePosition(int[] values) {
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Overriden hasNext.
     * @return true if array has even elements.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.position; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns current element and moves pointer forward.
     * @return current element.
     */
    @Override
    public Object next() {
        if (this.position >= this.values.length) {
            throw new NoSuchElementException();
        }
        int result = this.values[position++];

        if (result % 2 != 0) {
            for (int i = position; i < this.values.length; i++) {
                if (this.values[i] % 2 == 0) {
                    result = this.values[i];
                    position++;
                    break;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        return result;
    }
}
