package ru.job4j.generic;

/**
 * User store.
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Constructs new UserStore.
     * @param size size of the store.
     */
    public UserStore(int size) {
        super(size);
    }
}
