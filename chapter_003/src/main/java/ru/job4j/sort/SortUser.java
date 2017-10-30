package ru.job4j.sort;

import java.util.*;

/**
 * Sort user.
 */
public class SortUser {
    /**
     * Sorting list by age.
     * @param list list of users.
     * @return sorted TreeSet.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }
    /**
     * Sorting list by name length.
     * @param list list of users.
     * @return list, sorted by name length.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        Integer firstName = o1.getName().length();
                        Integer secondName = o2.getName().length();
                        return firstName.compareTo(secondName);
                    }
                }
        );
        return list;
    }
    /**
     * Sorting list by name and age.
     * @param list list of users.
     * @return list, sorted by name and age.
     */
    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> allFieldsComparator = new NameLengthComparator().thenComparing(new AgeComparator());
        list.sort(allFieldsComparator);
        return list;
    }

    /**
     * Comparator for name length.
     */
    public class NameLengthComparator implements Comparator<User> {
        /**
         * comparing by name length.
         * @param o1 user one.
         * @param o2 user two.
         * @return result of comparing.
         */
        @Override
        public int compare(User o1, User o2) {
            Integer firstName = o1.getName().length();
            Integer secondName = o2.getName().length();
            return firstName.compareTo(secondName);
        }
    }
    /**
     * Comparator for age.
     */
    public class AgeComparator implements Comparator<User> {
        /**
         * comparing by age.
         * @param o1 user one.
         * @param o2 user two.
         * @return result of comparing.
         */
        @Override
        public int compare(User o1, User o2) {
            Integer firstAge = o1.getAge();
            Integer secondAge = o2.getAge();
            return firstAge.compareTo(secondAge);
        }
    }
}
