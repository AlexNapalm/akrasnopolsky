package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sort user.
 */
public class SortUser {
    /**
     * Sorting list.
     * @param list list of users.
     * @return sorted TreeSet.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }
}
