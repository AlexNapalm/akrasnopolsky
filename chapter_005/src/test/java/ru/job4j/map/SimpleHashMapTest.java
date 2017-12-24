package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<User, String> shm = new SimpleHashMap<>();
    User user1 = new User("Alex", 0, null);
    User user2 = new User("Paul", 1, null);
    User user3 = new User("Nate", 2, null);
    User user4 = new User("Jack", 3, null);

    @Test
    public void whenInsertShouldReturnTrueAndGrowSize() {
        assertThat(shm.size(), is(0));
        assertThat(shm.insert(user1, "user1"), is(true));
        assertThat(shm.size(), is(1));
        assertThat(shm.insert(user2, "user2"), is(true));
        assertThat(shm.size(), is(2));
        assertThat(shm.insert(user2, "TEST"), is(false));
        assertThat(shm.size(), is(2));
    }

    @Test
    public void whenDeleteShouldReturnTrueAndReduceSize() {
        shm.insert(user1, "user1");
        shm.insert(user2, "user2");
        shm.insert(user3, "user3");

        assertThat(shm.size(), is(3));
        //try to add duplicate key
        assertThat(shm.insert(user3, "TEST"), is(false));
        //delete key user3
        assertThat(shm.delete(user3), is(true));
        assertThat(shm.size(), is(2));
        //adding key user3 again
        assertThat(shm.insert(user3, "TEST"), is(true));
        assertThat(shm.size(), is(3));
        //try to delete non-added key
        assertThat(shm.delete(user4), is(false));
    }

    @Test
    public void whenGetByKeyShouldReturnValue() {
        shm.insert(user1, "user1");
        shm.insert(user2, "user2");
        shm.insert(user3, "user3");

        assertThat(shm.get(user1), is("user1"));
        assertThat(shm.get(user2), is("user2"));
        assertThat(shm.get(user3), is("user3"));
        assertTrue(shm.get(user4) == null);
    }

    @Test (expected = NoSuchElementException.class)
    public void iterator() {
        shm.insert(user1, "user1");
        shm.insert(user2, "user2");
        shm.insert(user3, "user3");
        Iterator keyIterator = shm.keyIterator();
        assertThat(keyIterator.hasNext(), is(true));
        keyIterator.next();
        assertThat(keyIterator.hasNext(), is(true));
        keyIterator.next();
        assertThat(keyIterator.hasNext(), is(true));
        keyIterator.next();
        assertThat(keyIterator.hasNext(), is(false));
        keyIterator.next();

        Iterator valueIterator = shm.valueIterator();
        assertThat(valueIterator.hasNext(), is(true));
        valueIterator.next();
        assertThat(valueIterator.hasNext(), is(true));
        valueIterator.next();
        assertThat(valueIterator.hasNext(), is(true));
        valueIterator.next();
        assertThat(valueIterator.hasNext(), is(false));
        valueIterator.next();

    }
}