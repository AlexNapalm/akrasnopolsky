package ru.job4j.ioc.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MemoryStorage test class.
 */
public class MemoryStorageTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    private final MemoryStorage memoryStorage = (MemoryStorage) context.getBean("memoryStorage");
    private int index = 0;

    @Test
    public void whenAddUserThenInTheList() {
        User user = new User();
        user.setId(index++);
        user.setName("Mike");
        user.setEmail("mike@mail.ru");
        this.memoryStorage.add(user);
        User result = this.memoryStorage.getById(user.getId());
        assertSame(result, user);
    }

    @Test
    public void whenUpdateThenChangedUser() {
        User user = new User();
        user.setId(index++);
        user.setName("Mike");
        user.setEmail("mike@mail.ru");
        this.memoryStorage.add(user);
        System.out.println(this.memoryStorage.getAll().size());
        User result = this.memoryStorage.getById(user.getId());
        assertThat(result.getName(), is("Mike"));
        assertThat(result.getEmail(), is("mike@mail.ru"));
        user.setName("Changed");
        user.setEmail("changed@mail.ru");
        this.memoryStorage.update(user);
        User changed = this.memoryStorage.getById(user.getId());
        assertThat(changed.getName(), is("Changed"));
        assertThat(changed.getEmail(), is("changed@mail.ru"));
    }

    @Test
    public void whenDeleteUserThenNotExist() {
        User user = new User();
        user.setId(++index);
        user.setName("Bad");
        user.setEmail("bad@mail.ru");
        this.memoryStorage.add(user);
        User result = this.memoryStorage.getById(user.getId());
        assertSame(result, user);
        this.memoryStorage.delete(user.getId());
        assertNull(this.memoryStorage.getById(user.getId()));
    }
}
