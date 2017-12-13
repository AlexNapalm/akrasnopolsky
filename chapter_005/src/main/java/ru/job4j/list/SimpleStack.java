package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> implements Iterable<T> {
    /**
     * LinkedContainer based stack.
     */
    private LinkedContainer<T> container = new LinkedContainer();

    /**
     * Returns the last added element from the stack and removes it from container.
     * @return the last added element
     */
    public T pop() {
        T result = container.getElementByIndex(0);
        container.removeFirst();
        return result;
    }

    /**
     * Adds element to the stack.
     * @param value element to add.
     */
    public void push(T value) {
        container.addFirst(value);
    }

    /**
     * Return the size of the stack.
     * @return
     */
    public int size() {
        return  container.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return container.getElementByIndex(index++);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
