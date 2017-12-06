package ru.job4j.generic;

/**
 * Simple array class.
 */
public class SimpleArray<T> {

    Object[] objects;
    int index = 0;
    /**
     * Constructor.
     * @param size size of container.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }
    /**
     * adds a value to container.
     * @param value element to add.
     */
    public void add(T value) {
        this.objects[index++] = value;
    }
    /**
     * updates value in the container.
     * @param position position.
     * @param value new value.
     */
    public void update(int position, T value) {
        this.objects[position] = value;
    }
    /**
     * deletes value from the container.
     * @param position position to delete.
     */
    public void delete(int position) {
        System.arraycopy(this.objects, position + 1, this.objects, position, this.index - position - 1);
        this.index--;
    }

    /**
     * Returns the value on the position.
     * @param position position of the value.
     * @return value on the position.
     */
    public T get(int position) {
        return (T) this.objects[position];
    }

    /**
     * Return size of array.
     * @return size of array.
     */
    public int getSize() {
        return this.index;
    }
}
