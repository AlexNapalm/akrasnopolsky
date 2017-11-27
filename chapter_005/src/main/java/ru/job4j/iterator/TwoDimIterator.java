package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for 2-dimensions array.
 */
public class TwoDimIterator implements Iterator {
    /**
     * Array.
     */
    private int[][] values;
    /**
     * Count for the pointer.
     */
    private int count = 0;
    /**
     * Count for array column.
     */
    private int col = 0;
    /**
     * Count for array row.
     */
    private int row = 0;

    /**
     * Constructor.
     * @param values array.
     */
    public TwoDimIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Returns total elements in array.
     * @return total elements.
     */
    public int getElements() {
        int elements = 0;
        for (int i = 0; i < this.values.length; i++) {
            elements += this.values[i].length;
        }
        return elements;
    }

    /**
     * Overriden hasNext method.
     * @return true, if array has next element.
     */
    @Override
    public boolean hasNext() {
        return count < getElements();
    }

    /**
     * Returns current element and moves pointer one step forward.
     * @return current element.
     */
    @Override
    public Object next() {
        if (count == this.getElements()) {
            throw new NoSuchElementException();
        } else {
            count++;
        }
        if (col == this.values[row].length) {
            row++;
            col = 0;
        }
        return this.values[row][col++];
    }
}
