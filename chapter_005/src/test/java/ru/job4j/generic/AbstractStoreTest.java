package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {

    @Test
    public void whenAddModelShouldReturnRightModel() {
        AbstractStore<User> users = new UserStore(5);
        AbstractStore<Role> roles = new RoleStore(5);
        User user1 = new User("11");
        User user2 = new User("22");
        User user3 = new User("33");
        User user4 = new User("44");
        User user5 = new User("55");
        Role role1 = new Role("111");
        Role role2 = new Role("222");
        Role role3 = new Role("333");
        Role role4 = new Role("444");
        Role role5 = new Role("555");

        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user1);
        users.add(user5);
        roles.add(role5);
        roles.add(role4);
        roles.add(role3);
        roles.add(role2);
        roles.add(role1);

         assertThat(users.get(0), is(user2));
         assertThat(users.get(1), is(user3));
         assertThat(users.get(2), is(user4));
         assertThat(users.get(3), is(user1));
         assertThat(users.get(4), is(user5));
         assertThat(roles.get(0), is(role5));
         assertThat(roles.get(1), is(role4));
         assertThat(roles.get(2), is(role3));
         assertThat(roles.get(3), is(role2));
         assertThat(roles.get(4), is(role1));
    }

    @Test
    public void whenUpdateShouldReturnUpdatedElement() {
        AbstractStore<User> users = new UserStore(2);
        User user1 = new User("123");
        User user2 = new User("456");
        User user3 = new User("789");

        users.add(user1);
        users.add(user2);
        users.update("456", user3);

        assertThat(users.get(1), is(user3));
    }

    @Test
    public void whenDeleteShouldNotReturnDeletedItem() {
        AbstractStore<User> users = new UserStore(2);
        User user1 = new User("123");
        User user2 = new User("456");

        users.add(user1);
        users.add(user2);
        users.delete("123");

        assertThat(users.get(0), is(user2));
    }
}
