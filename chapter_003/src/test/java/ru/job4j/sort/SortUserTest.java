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
                        new User("Dime", 21),
                        new User("Diamond", 21)
                )
        );
        SortUser su = new SortUser();
        Set<User> result = su.sort(list);
        Set<User> expected = new TreeSet<>(list);
        assertThat(result, is(expected));
    }

    /**
     * Test for sortNameLength.
     */
    @Test
    public void whenListOfUsersThenSortedByNameListOfUsers() {
        List<User> list = new ArrayList<>();
        User user1 = new User("Nick", 25);
        User user2 = new User("Paolo", 31);
        User user3 = new User("Ed", 20);
        User user4 = new User("Ted", 23);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        SortUser su = new SortUser();
        List<User> result = su.sortNameLength(list);
        List<User> expected = new ArrayList<>();
        expected.add(user3);
        expected.add(user4);
        expected.add(user1);
        expected.add(user2);
        assertThat(result, is(expected));
    }

    /**
     * Test for sortByAllFields.
     */
    @Test
    public void whenListOfUsersThenSortedByNameAndAgeListOfUsers() {
        List<User> list = new ArrayList<>();
        User user1 = new User("Serge", 25);
        User user2 = new User("Ivan", 30);
        User user3 = new User("Serge", 20);
        User user4 = new User("Ivan", 25);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        SortUser su = new SortUser();
        List<User> result = su.sortByAllFields(list);
        List<User> expected = new ArrayList<>();
        expected.add(user4);
        expected.add(user2);
        expected.add(user3);
        expected.add(user1);
        assertThat(result, is(expected));
    }
}
