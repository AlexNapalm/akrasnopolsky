package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> {

    /**
     * Default capacity of the table.
     */
    public static final int DEFAULT_CAPACITY = 16;
    /**
     * Load factor.
     */
    public static final double LOAD_FACTOR = 0.75f;
    /**
     * Array of pairs key + value.
     */
    private Entry[] table;
    /**
     * Size of the map.
     */
    private int size = 0;

    /**
     * Constructs simple hashmap.
     */
    public SimpleHashMap() {
        this.table = new Entry[DEFAULT_CAPACITY];
    }

    /**
     * Adds element to the set.
     * @param key key.
     * @param value mapping.
     * @return true, if element was added and false if not.
     */
    public boolean insert(K key, V value) {
        if (size >= table.length * LOAD_FACTOR) {
            resize(table.length * 2);
        }
        int index = key.hashCode() & (table.length - 1);
        if (table[index] != null) {
            return false;
        }
        table[index] = new Entry(key, value);
        size++;
        return true;
    }

    /**
     * Gets mapping by its key.
     * @param key key.
     * @return element - mapping.
     */
    public V get(K key) {
        V result = null;
        int index = key.hashCode() & (table.length - 1);
        if (table[index] != null) {
            result = (V) table[index].getValue();
        }
        return result;
    }

    /**
     * Removes element from the map.
     * @param key key.
     * @return true if element was removed and false if not.
     */
    public boolean delete(K key) {
        int index = key.hashCode() & (table.length - 1);
        if (table[index] != null) {
            table[index] = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Returns number of elements.
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * Enlarges array.
     * @param newCapacity new capacity.
     */
    private void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    /**
     * Transfers elements from old table to the new table with new indexes based on elements hash code.
     * @param newTable
     */
    private void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int index = table[i].hashCode() & (newTable.length - 1);
                newTable[index] = table[i];
            }
        }
    }

    public Iterator keyIterator() {
        return new Iterator() {
            int position = 0;

            @Override
            public boolean hasNext() {
                if (position >= table.length) {
                    return false;
                }
                if (table[position] != null) {
                    return true;
                } else {
                    for (int i = position; i < table.length; i++) {
                        if (table[i] != null) {
                            position = i;
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (hasNext()) {
                    return (K) table[position++].getKey();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    public Iterator valueIterator() {
        return new Iterator() {

            int position = 0;

            @Override
            public boolean hasNext() {
                if (position >= table.length) {
                    return false;
                }
                if (table[position] != null) {
                    return true;
                } else {
                    for (int i = position; i < table.length; i++) {
                        if (table[i] != null) {
                            position = i;
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public V next() {
                if (hasNext()) {
                    return (V) table[position++].getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    public class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key)
                    && Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
