package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Iterable<T> {

    /**
     * LinkedContainer based stack.
     */
    private LinkedContainer<T> container = new LinkedContainer();

    /**
     * Returns the head element and removes it from container.
     * @return the first added element.
     */
    public T poll() {
        T result = container.getElementByIndex(0);
        container.removeFirst();
        return result;
    }

    /**
     * Adds element to the container.
     * @param value element to add.
     */
    public void push(T value) {
        container.addLast(value);
    }

    /**
     * Returns the size of the container.
     * @return
     */
    public int size() {
        return container.size();
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
