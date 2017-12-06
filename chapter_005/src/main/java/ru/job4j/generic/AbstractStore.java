package ru.job4j.generic;

/**
 * Abstract store.
 * @param <T> type of stored elements.
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Array of elements.
     */
    private SimpleArray<T> store;

    /**
     * Constructor.
     * @param size size of the stor.
     */
    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * Adds element to store.
     * @param model element to add.
     */
    @Override
    public void add(T model) {
        this.store.add(model);
    }

    /**
     * Updates element in the store.
     * @param id id of the updated element.
     * @param model element to update with.
     */
    @Override
    public void update(String id, T model) {
        for (int i = 0; i < store.getSize(); i++) {
            if (id.equals(store.get(i).getId())) {
                store.update(i, model);
                break;
            }
        }
    }

    /**
     * Deletes element from the store.
     * @param id if of the element to delete.
     */
    @Override
    public void delete(String id) {
        for (int i = 0; i < store.getSize(); i++) {
            if (id.equals(store.get(i).getId())) {
                store.delete(i);
                break;
            }
        }
    }

    public T get(int position) {
        return this.store.get(position);
    }
}
