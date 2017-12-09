package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    /**
     * Array of elements.
     */
    private Object[] container;
    /**
     * Actual filled size of container.
     */
    private int position = 0;
    /**
     * Constant for default capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Step for growing size.
     */
    private static final int GROW_STEP = 50;

    /**
     * Default constructor.
     */
    public DynamicList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Construct container with custom size.
     * @param size size of the container;
     */
    public DynamicList(int size) {
        this.container = new Object[size];
    }

    /**
     * Adds an element.
     * @param value element to add.
     */
    public void add(E value) {
        if (position >= this.container.length) {
            grow();
        }
        this.container[position++] = value;
    }

    /**
     * Returns element by index.
     * @param index index.
     * @return element.
     */
    public E get(int index) {
        return (E) this.container[index];
    }

    /**
     * Return size of the container;
     * @return size.
     */
    public int size() {
        return this.container.length;
    }

    /**
     * Grows the size of container if there is no space to store elements.
     */
    private void grow() {
        int oldCapacity = this.container.length;
        int newCapacity = oldCapacity + GROW_STEP;
        this.container = Arrays.copyOf(this.container, newCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.container);
    }

    /**
     * Implementation of iterator for DynamicList;
     */
    private class Itr implements Iterator<E> {
        private Object[] values;
        private int index = 0;

        public Itr(Object[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public E next() {
            if (hasNext()) {
                return (E) this.values[index++];
            } else {
                throw new NoSuchElementException();
            }

        }
    }
}
