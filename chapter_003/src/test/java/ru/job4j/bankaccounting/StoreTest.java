package ru.job4j.bankaccounting;

import java.util.*;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    /**
     * addUser test.
     */
    @Test
    public void ifAddUserThenStoreHasUser() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        store.addUser(user);
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user, user.getUserAccounts());
        Map<User, List<Account>> result = store.getClientsDataBase();
        assertThat(result, is(expected));
    }
    /**
     * deleteUser test.
     */
    @Test
    public void ifRemoveUserThenStoreHasNoSuchUser() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        User user2 = new User("Bob", 34567);
        store.addUser(user);
        store.addUser(user2);
        store.deleteUser(user);
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(user2, user.getUserAccounts());
        Map<User, List<Account>> result = store.getClientsDataBase();
        assertThat(result, is(expected));
    }
    /**
     * addAccountToUser test.
     */
    @Test
    public void ifAddAccountThenUserHasAccount() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        User user2 = new User("Bob", 34567);
        store.addUser(user);
        store.addUser(user2);
        Account account = new Account(98765);
        store.addAccountToUser(user2, account);
        List<Account> expected = new ArrayList<>();
        expected.add(account);
        List<Account> result = store.getUserAccounts(user2);
        assertThat(result, is(expected));
    }
    /**
     * deleteAccountFromUser test.
     */
    @Test
    public void whenRemoveAccountThenUserHasNoSuchAccount() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        User user2 = new User("Bob", 34567);
        store.addUser(user);
        store.addUser(user2);
        Account account = new Account(98765);
        Account account2 = new Account(98765);
        store.addAccountToUser(user2, account);
        store.addAccountToUser(user2, account2);
        store.deleteAccountFromUser(user2, account);
        List<Account> expected = new ArrayList<>();
        expected.add(account2);
        List<Account> result = store.getUserAccounts(user2);
        assertThat(result, is(expected));
    }
    /**
     * findUserByName test.
     */
    @Test
    public void whenKeyEqualsNameThenGetUser() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        User user2 = new User("Bob", 34567);
        store.addUser(user);
        store.addUser(user2);
        User result = store.findUserByName("Bob");
        assertThat(result, is(user2));
    }
    /**
     * findUserByPassport test.
     */
    @Test
    public void whenKeyEqualsNameThenGetNull() {
        Store store = new Store();
        User user = new User("Alex", 12345);
        User user2 = new User("Bob", 34567);
        store.addUser(user);
        store.addUser(user2);
        User result = store.findUserByPassport(12345);
        User expected = user;
        assertThat(result, is(expected));
    }
    /**
     * transferMoney test.
     */
    @Test
    public void whenTransferMoneyThenValueRaise() {
        Store store = new Store();
        User srcUser = new User("Alex", 12345);
        User dstUser = new User("Bob", 34567);
        store.addUser(srcUser);
        store.addUser(dstUser);
        Account srcAccount = new Account(1234);
        srcAccount.setValue(2500);
        Account dstAccount = new Account(4321);
        dstAccount.setValue(1000);
        store.addAccountToUser(srcUser, srcAccount);
        store.addAccountToUser(dstUser, dstAccount);
        store.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 400);
        assertThat(srcAccount.getValue(), is(2100d));
        assertThat(dstAccount.getValue(), is(1400d));
    }
    /**
     * transferMoney test.
     */
    @Test
    public void whenNotEnoughMoneyThenFalse() {
        Store store = new Store();
        User srcUser = new User("Alex", 12345);
        User dstUser = new User("Bob", 34567);
        store.addUser(srcUser);
        store.addUser(dstUser);
        Account srcAccount = new Account(1234);
        srcAccount.setValue(2500);
        Account dstAccount = new Account(4321);
        dstAccount.setValue(1000);
        store.addAccountToUser(srcUser, srcAccount);
        store.addAccountToUser(dstUser, dstAccount);
        boolean result = store.transferMoney(srcUser, srcAccount, dstUser, dstAccount, 2600);
        assertThat(result, is(false));
    }
}
