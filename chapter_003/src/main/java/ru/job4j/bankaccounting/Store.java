package ru.job4j.bankaccounting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    /**
     * Hashmap with User as a key and list of accounts as a mapping.
     */
    private Map<User, List<Account>> clientsDataBase;
    /**
     * Constructs new store and creates new hashmap.
     */
    public Store() {
        clientsDataBase = new HashMap<>();
    }
    /**
     * Adding user to db.
     * @param user user.
     */
    public void addUser(User user) {
        clientsDataBase.put(user, user.getUserAccounts());
    }

    /**
     * Deleting user from db.
     * @param user user.
     */
    public void deleteUser(User user) {
        clientsDataBase.remove(user);
    }
    /**
     * Adds account to user.
     * @param user user.
     * @param account account.
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> result = user.getUserAccounts();
        result.add(account);
        user.setUserAccounts(result);
    }
    /**
     * Deletes account from user.
     * @param user user.
     * @param account account.
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> result = user.getUserAccounts();
        result.remove(account);
        user.setUserAccounts(result);
    }
    /**
     * User accounts getter.
     * @param user user.
     * @return
     */
    public List<Account> getUserAccounts(User user) {
        return user.getUserAccounts();
    }
    /**
     * Clients db getter.
     * @return map with users and accounts.
     */
    public Map<User, List<Account>> getClientsDataBase() {
        return clientsDataBase;
    }
    /**
     * Finds user by name.
     * @param key key for search.
     * @return user.
     */
    public User findUserByName(String key) {
        User result = null;
        for (User user : clientsDataBase.keySet()) {
            if (key.equals(user.getName())) {
                result = user;
            }
        }
        return result;
    }
    /**
     * Finds user by passport.
     * @param key key for search.
     * @return user.
     */
    public User findUserByPassport(int key) {
        User result = null;
        for (User user : clientsDataBase.keySet()) {
            if (key == user.getPassport()) {
                result = user;
                break;
            }
        }
        return result;
    }
    /**
     * Finds account by requisites/
     * @param user user.
     * @param key key for search.
     * @return account.
     */
    public Account findAccountByRequisites(User user, int key) {
        List<Account> accountsList = user.getUserAccounts();
        Account result = null;
        for (Account account : accountsList) {
            if (key == account.getRequisites()) {
                result = account;
                break;
            }
        }
        return result;
    }
    /**
     * Transfers money from account to account.
     * @param srcUser source user.
     * @param srcAccount source account.
     * @param dstUser destination user.
     * @param dstAccount destination account.
     * @param amount value to transfer.
     * @return true, if success , and false if failure to transfer.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        if (findAccountByRequisites(srcUser, srcAccount.getRequisites()).getValue() < amount) {
            return false;
        }
        double newSrcValue = findAccountByRequisites(srcUser, srcAccount.getRequisites()).getValue() - amount;
        findAccountByRequisites(srcUser, srcAccount.getRequisites()).setValue(newSrcValue);
        double newDstValue = findAccountByRequisites(dstUser, dstAccount.getRequisites()).getValue() + amount;
        findAccountByRequisites(dstUser, dstAccount.getRequisites()).setValue(newDstValue);
        return true;
    }
}
