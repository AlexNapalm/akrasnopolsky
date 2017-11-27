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
    private int position = 0;
    /**
     * Constructs iterator.
     * @param values array.
     */
    public EvenIterator(int[] values) {
        this.values = values;
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
        int result = this.values[position];
        if (result % 2 != 0 && hasNext()) {
            for (int i = position; i < this.values.length; i++) {
                if (this.values[i] % 2 == 0) {
                    result = this.values[i];
                    position = ++i;
                    break;
                }
            }
        } else {
            throw new NoSuchElementException();
        }


        return result;
    }
}
