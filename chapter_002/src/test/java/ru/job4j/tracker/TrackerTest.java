package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * тестовый класс для тестирования класса Tracker.
 */
public class TrackerTest {
    /**
     * тест метода add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * тест метода update.
     */
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.update(next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * тест метода delete.
     */
    @Test
    public void whenDeleteItemThenTrackerHasNoSuchItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 456L);
        Item item3 = new Item("test3", "testDescription3", 789L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item2);
        List<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(item3);
        assertThat(tracker.findAll(), is(expected));
    }

    /**
     * тест метода findAll.
     */
    @Test
    public void whenTrackerHasItemsReturnArrayOfItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 456L);
        Item item3 = new Item("test3", "testDescription3", 789L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(item2);
        expected.add(item3);
        assertThat(tracker.findAll(), is(expected));
    }

    /**
     * тест метода findByName.
     */
    @Test
    public void whenSearchByNameThenReturnArrayOfItemsWithSuchName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        Item item3 = new Item("test1", "testDescription3", 125L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item3);
        assertThat(tracker.findByName("test1"), is(expected));
    }

    /**
     * тест метода findById.
     */
    @Test
    public void whenSearchByIdThenReturnArrayOfItemsWithSuchId() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDescription2", 124L);
        Item item3 = new Item("test1", "testDescription3", 125L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findById(item2.getId()), is(item2));
    }
}
