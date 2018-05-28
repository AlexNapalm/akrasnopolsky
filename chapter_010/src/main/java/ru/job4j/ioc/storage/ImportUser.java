package ru.job4j.ioc.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application class.
 */
public class ImportUser {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        JdbcStorage jdbcStorage = (JdbcStorage) context.getBean("jdbcStorage");

        User user = new User();
        user.setName("testName");
        user.setEmail("email@email.ru");
        jdbcStorage.add(user);
        System.out.println(jdbcStorage.getById(1));
    }
}
