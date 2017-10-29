package ru.job4j.sort;

import java.util.*;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SortUser.
 */
public class SortUserTest {
    /**
     * Testing of sort.
     */
    @Test
    public void whenListOfUsersThenSortedSetOfUsers() {
        List<User> list = new ArrayList<>();
        list.addAll(
                Arrays.asList(
                        new User("Alex", 26),
                        new User("John", 23),
                        new User("Ian", 30),
                        new User("Dime", 21)
                )
        );
        SortUser su = new SortUser();
        Set<User> result = su.sort(list);

        Set<User> expected = new TreeSet<>();
        expected.add(new User("Dime", 21));
        expected.add(new User("John", 23));
        expected.add(new User("Alex", 26));
        expected.add(new User("Ian", 30));

        assertThat(result, is(expected));
    }
}
