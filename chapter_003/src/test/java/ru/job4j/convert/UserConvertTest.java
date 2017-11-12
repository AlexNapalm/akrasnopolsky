package ru.job4j.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования класса UserConvert.
 */
public class UserConvertTest {
    /**
     * Тест метода process.
     */
    @Test
    public void whenListOfUsersConvertToHashMap() {
        List<User> testList = new ArrayList<>();
        List<User> testList1 = new LinkedList<>();
        User user1 = new User(1, "Alex", "Moscow");
        User user2 = new User(2, "John", "Ottawa");
        User user3 = new User(3, "Peter", "London");
        User user4 = new User(4, "Michael", "Boston");
        User user5 = new User(5, "Dime", "Madrid");
        testList.add(user1);
        testList.add(user2);
        testList.add(user3);
        testList.add(user4);
        testList.add(user5);

        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, user1);
        expected.put(2, user2);
        expected.put(3, user3);
        expected.put(4, user4);
        expected.put(5, user5);

        UserConvert uc = new UserConvert();
        HashMap<Integer, User> result = uc.process(testList);
        assertThat(result, is(expected));
    }
}
