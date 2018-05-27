package ru.job4j.ioc.examples;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class UserStorageTest {
    /**
     * Пример демонстрирует получение объекта-бина MemoryStorage посредством Spring.
     */
    @Test
    public void whenLoadContextShouldGetBeansVer1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }

    /**
     * Пример демонстрирует получение объекта-бина UserStorage посредством Spring. В данном случае Spring автоматически
     * подставляет бин MemoryStorage в конструктор UserStorage, согласно конфигурации.
     */
    @Test
    public void whenLoadContextShouldGetBeansVer2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        userStorage.add(new User());
        assertNotNull(userStorage);
    }
}