package ru.job4j.ioc.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {

    private final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        System.out.println("UserStorage add method implementation");
        this.storage.add(user);
    }
}
