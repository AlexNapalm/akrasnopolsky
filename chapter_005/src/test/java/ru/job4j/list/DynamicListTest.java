package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * DynamicList test class.
 */
public class DynamicListTest {
    DynamicList<Integer> intList = new DynamicList<>();
    DynamicList<String> strList = new DynamicList<>(3);

    @Before
    public void setUp() {
        intList.add(1);
        intList.add(3);
        intList.add(2);
        intList.add(4);
        intList.add(6);
        intList.add(5);
        intList.add(8);
        intList.add(7);
        intList.add(9);
        intList.add(10);
        strList.add("one");
        strList.add("two");
        strList.add("three");
    }

    @Test
    public void whenAddItemShouldReturnRightItem() {
        assertThat(intList.get(0), is(1));
        assertThat(intList.get(1), is(3));
        assertThat(intList.get(2), is(2));
        assertThat(intList.get(3), is(4));
        assertThat(intList.get(4), is(6));
        assertThat(intList.get(5), is(5));
        assertThat(intList.get(6), is(8));
        assertThat(intList.get(7), is(7));
        assertThat(intList.get(8), is(9));
        assertThat(intList.get(9), is(10));

        assertThat(strList.get(0), is("one"));
        assertThat(strList.get(1), is("two"));
        assertThat(strList.get(2), is("three"));
    }

    @Test
    public void whenContainerFullThenGrowTheSize() {
        assertThat(intList.size(), is(10));
        intList.add(11);
        assertThat(intList.size(), is(60));

        assertThat(strList.size(), is(3));
        strList.add("four");
        assertThat(strList.size(), is(53));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorTest() {
        Iterator iterator = intList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(7));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(9));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(10));
        iterator.next();
    }
}