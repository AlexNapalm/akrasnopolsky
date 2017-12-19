package ru.job4j.set;

public class SimpleHashSet<E> {
    /**
     * Default capacity of the table.
     */
    public static final int DEFAULT_CAPACITY = 16;
    /**
     * Load factor.
     */
    public static final double LOAD_FACTOR = 0.75;
    /**
     * Array of elements.
     */
    private E[] table;
    /**
     * Number of elements.
     */
    private int size = 0;

    /**
     * Constructs simple hashset.
     */
    public SimpleHashSet() {
        this.table = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adds element to the set.
     * @param e element.
     * @return true, if element was added and false if not.
     */
    public boolean add(E e) {
        if (size >= table.length * LOAD_FACTOR) {
            resize(table.length * 2);
        }
        int index = e.hashCode() & (table.length - 1);
        if (contains(e)) {
            return false;
        }
        table[index] = e;
        size++;
        return true;
    }

    /**
     * Checks if set contains such element.
     * @param e element.
     * @return true if contains and false if not.
     */
    public boolean contains(E e) {
        int index = e.hashCode() & (table.length - 1);
        return table[index] != null && table[index].equals(e);
    }

    /**
     * Removes element from the set.
     * @param e element.
     * @return ture if element was removed and false if not.
     */
    public boolean remove(E e) {
        int index = e.hashCode() & (table.length - 1);
        if (contains(e)) {
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
     * Return real array length.
     * @return size of the array.
     */
    public int getTableSize() {
        return table.length;
    }

    /**
     * Enlarges array.
     * @param newCapacity new capacity.
     */
    private void resize(int newCapacity) {
        E[] newTable = (E[]) new Object[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    /**
     * Transfers elements from old table to the new table with new indexes based on elements hash code.
     * @param newTable
     */
    private void transfer(E[] newTable) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int index = table[i].hashCode() & (newTable.length - 1);
                newTable[index] = table[i];
            }

        }
    }

}
