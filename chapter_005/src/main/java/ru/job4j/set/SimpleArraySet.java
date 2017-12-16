package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArraySet<E> implements Iterable<E> {
    /**
     * Array to store elements.
     */
    private E[] container;
    /**
     * Number of added elements.jy o
     */
    private int size = 0;
    /**
     * Default capacity of container.
     */
    private final static int DEFAULT_CAPACITY = 10;
    /**
     * Grow step.
     */
    private static final int GROW_STEP = 50;

    /**
     * Cpnstructs simple set container.
     */
    public SimpleArraySet() {
        container = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adds element.
     * @param e element.
     */
    public void add(E e) {
        boolean duplicate = false;
        for (Object item : container) {
            if (e.equals(item)) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            if (size >= size()) {
                grow();
            }
            container[size++] = e;
        }
    }

    /**
     * Increases container capacity.
     */
    private void grow() {
        int oldCapacity = size();
        int newCapacity = oldCapacity + GROW_STEP;
        this.container = Arrays.copyOf(this.container, newCapacity);
    }

    /**
     * Returns container capacity.
     * @return
     */
    public int size() {
        return this.container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int position = 0;

            @Override
            public boolean hasNext() {
                return position < size();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[position++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
