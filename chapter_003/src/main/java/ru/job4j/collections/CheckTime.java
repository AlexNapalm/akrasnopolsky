package ru.job4j.collections;

import java.util.*;

/**
 * Класс для измерения времени добавления и удаления элементов из коллекций.
 */
public class CheckTime {

    /**
     * Добавление строк в коллекцию.
     * @param collection целевая коллекция.
     * @param amount количество элементов для добавления.
     * @return количество миллисекунд, затраченное на работу метода.
     */
    public long add(Collection<String> collection, int amount) {
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(Integer.toString(random.nextInt(65356)));
        }
        long finish = System.currentTimeMillis();

        return finish - start;
    }

    /**
     * Удаление строк из коллекции.
     * @param collection целевая коллекция.
     * @param amount количество элементов для удаления.
     * @return количество миллисекунд, затраченное на работу метода.
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        Iterator iterator = collection.iterator();
        for (int i = 0; i < amount && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        CheckTime ct = new CheckTime();
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new LinkedList<String>();
        Set<String> treeSet = new TreeSet<String>();

        System.out.println(String.format("adding to array list took: %s", ct.add(arrayList, 1000000)));
        System.out.println(String.format("adding to linked list took: %s", ct.add(linkedList, 1000000)));
        System.out.println(String.format("adding to tree set took: %s", ct.add(treeSet, 1000000)));

        System.out.println(String.format("deleting from array list took: %s", ct.delete(arrayList, 50000)));
        System.out.println(String.format("deleting from linked list took: %s", ct.delete(linkedList, 50000)));
        System.out.println(String.format("deleting from tree set took: %s", ct.delete(treeSet, 50000)));

    }
}
