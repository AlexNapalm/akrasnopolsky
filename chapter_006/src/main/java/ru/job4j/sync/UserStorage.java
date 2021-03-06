package ru.job4j.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {

    /**
     * Container for users.
     */
    @GuardedBy("this")
    private List<User> users;

    /**
     * Constructs UserStorage.
     */
    public UserStorage() {
        this.users = new ArrayList<>(100);
    }

    /**
     * Add user to storage.
     * @param user user
     * @return true, if added.
     */
    public synchronized boolean add(User user) {
        return this.users.add(user);
    }

    /**
     * Update user.
     * @param user user to update.
     * @return true, if updated.
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.users.contains(user)) {
            delete(user);
            add(user);
            result = true;
        }
        return result;
    }

    /**
     * Delete user.
     * @param user user to delete.
     * @return true, if deleted.
     */
    public synchronized boolean delete(User user) {
        return this.users.remove(user);
    }

    /**
     * Transfers money between users.
     * @param fromId ID of the User, from which account money transfer.
     * @param toId ID of the User, to which account money transfer.
     * @param amount amount.
     * @return true, if transfer is successful.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = null;
        User userTo = null;
        for (User user : this.users) {
            if (user.getId() == fromId) {
                userFrom = user;
            } else if (user.getId() == toId) {
                userTo = user;
            }
        }
        if (userFrom != null && userTo != null) {
            int accountFrom = userFrom.getAmount();
            int accountTo = userTo.getAmount();
            if (accountFrom >= amount) {
                userFrom.setAmount(accountFrom - amount);
                userTo.setAmount(accountTo + amount);
                result = true;
            }
        }
        return result;
    }
}
