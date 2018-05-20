package ru.job4j.carsale.dao;

import org.junit.Test;
import ru.job4j.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void createAndGetByIdTest() {
        User user = new User();
        user.setName("name");
        user.setLogin("root");
        user.setPassword("root");
        user.setPhone(88002000600L);
        this.userDao.create(user);
        User result = userDao.getById(user.getId());
        assertThat(user, is(result));
    }

    @Test
    public void isRegisteredTest() {
        User user = new User();
        user.setName("name");
        user.setLogin("login");
        user.setPassword("pass");
        user.setPhone(88002000600L);
        this.userDao.create(user);
        User result = userDao.isRegistered("login", "pass");
        assertThat(user, is(result));
    }



}