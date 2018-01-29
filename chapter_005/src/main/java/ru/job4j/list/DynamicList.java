package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
@ThreadSafe
public class DynamicList<E> implements Iterable<E> {
    /**
     * Lock object for sync.
     */
    private final Object lock;

    /**
     * Array of elements.
     */
    @GuardedBy("lock")
    private Object[] container;
    /**
     * Actual filled size of container.
     */
    @GuardedBy("lock")
    private int size = 0;
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
        this.lock = new Object();
    }

    /**
     * Construct container with custom size.
     * @param size size of the container;
     */
    public DynamicList(int size) {
        this.container = new Object[size];
        this.lock = new Object();
    }

    /**
     * Adds an element.
     * @param value element to add.
     */
    @GuardedBy("lock")
    public void add(E value) {
        synchronized (this.lock) {
            if (size >= this.container.length) {
                grow();
            }
            this.container[size++] = value;
        }
    }
    /**
     * Returns element by index.
     * @param index index.
     * @return element.
     */
    @GuardedBy("lock")
    public E get(int index) {
        synchronized (this.lock) {
            return (E) this.container[index];
        }
    }

    /**
     * Return size of the container;
     * @return size.
     */
    @GuardedBy("lock")
    public int size() {
        synchronized (this.lock) {
            return this.container.length;
        }
    }

    /**
     * Grows the size of container if there is no space to store elements.
     */
    @GuardedBy("lock")
    private void grow() {
        synchronized (this.lock) {
            int oldCapacity = this.container.length;
            int newCapacity = oldCapacity + GROW_STEP;
            this.container = Arrays.copyOf(this.container, newCapacity);
        }
    }

    @GuardedBy("lock")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @GuardedBy("lock")
            int position = 0;

            @GuardedBy("lock")
            @Override
            public boolean hasNext() {
                synchronized (lock) {
                    return position < size;
                }
            }
            @GuardedBy("lock")
            @Override
            public E next() {
                synchronized (lock) {
                    if (hasNext()) {
                        return (E) container[position++];
                    } else {
                        throw new NoSuchElementException();
                    }
                }
            }
        };
    }
}
