package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования трекера через эмуляцию ответов пользователя.
 */
public class StubInputTest {
    /**
     * тест добавления заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Тест редактирования заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "name", "desc", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findAll()[0].getName(), is("name"));
    }

    /**
     * тест удаления заявки.
     */
    @Test
    public void whenDeleteItemThenTrackerHasNoThis() {
        // создаем Tracker
        Tracker tracker = new Tracker();
        // создаем три заявки
        Item item1 = new Item("name1", "desc1", 123L);
        Item item2 = new Item("name2", "desc2", 123L);
        Item item3 = new Item("name3", "desc3", 123L);
        // вручную добавляем заявки в трекер
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        // создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"3", item2.getId(), "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        Item[] expected = {item1, item3};
        assertThat(tracker.findAll(), is(expected));
    }

    /**
     * тест поиска заявки по id.
     */
    @Test
    public void whenSearchByIdThenPrintThisItem() {
        // создаем Tracker
        Tracker tracker = new Tracker();
        // создаем три заявки
        Item item1 = new Item("name1", "desc1", 123L);
        Item item2 = new Item("name2", "desc2", 123L);
        Item item3 = new Item("name3", "desc3", 123L);
        // вручную добавляем заявки в трекер
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        // создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"4", item2.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item2.getId()), is(item2));
    }
    /**
     * тест поиска заявки по имени.
     */
    @Test
    public void whenSearchByNameThenPrintAllItemsWithSuchName() {
        // создаем Tracker
        Tracker tracker = new Tracker();
        // создаем три заявки
        Item item1 = new Item("name1", "desc1", 123L);
        Item item2 = new Item("name2", "desc2", 123L);
        Item item3 = new Item("name3", "desc3", 123L);
        // вручную добавляем заявки в трекер
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        // создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"5", "name2", "6"});
        Item[] expected = {item2};
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("name2"), is(expected));
    }
    /**
     * тест печати всех заявок.
     */
    @Test
    public void whenShowAllItemsThenPrintAllItems() {
        Tracker tracker = new Tracker();
        // создаем три заявки
        Item item1 = new Item("name1", "desc1", 123L);
        Item item2 = new Item("name2", "desc2", 123L);
        Item item3 = new Item("name3", "desc3", 123L);
        // вручную добавляем заявки в трекер
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        // создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"1", "6"});
        Item[] expected = {item1, item2, item3};
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(expected));
    }
}
