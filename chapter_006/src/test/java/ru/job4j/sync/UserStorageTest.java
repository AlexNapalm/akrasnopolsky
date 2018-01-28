package ru.job4j.sync;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    @Test
    public void whenTwoThreadsWorkWithStorageThenRightData() throws InterruptedException {
        User user1 = new User(1, 1000);
        User user2 = new User(2, 900);
        User user3 = new User(3, 800);
        User user4 = new User(4, 700);
        UserStorage us = new UserStorage();
        us.add(user1);
        us.add(user2);
        us.add(user3);
        us.add(user4);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                us.transfer(1, 4, 200);
                us.transfer(2, 3, 100);
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                us.transfer(1, 3, 150);
                us.transfer(3, 2, 250);
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertThat(user1.getAmount(), is(650));
        assertThat(user2.getAmount(), is(1050));
        assertThat(user3.getAmount(), is(800));
        assertThat(user4.getAmount(), is(900));
    }
}