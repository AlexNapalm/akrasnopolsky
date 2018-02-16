package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class NonBlockCache {

    private Map<Integer, User> cache;

    public NonBlockCache() {
        this.cache = new ConcurrentHashMap<>(100);
    }

    public void add(User user) {
        this.cache.putIfAbsent(user.getId(), user);
    }

    public void delete(User user) {
        this.cache.remove(user.getId());
    }

    public void update(User updatedUser) throws OptimisticException {
        cache.computeIfPresent(updatedUser.getId(), new BiFunction<Integer, User, User>() {
            @Override
            public User apply(Integer integer, User user) {
                if (user.getVersion() == updatedUser.getVersion()) {
                    user.setName(updatedUser.getName());
                    return user;
                } else {
                    throw new OptimisticException();
                }
            }
        });
    }
}
