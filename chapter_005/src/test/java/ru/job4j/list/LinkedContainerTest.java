package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedContainerTest {

    LinkedContainer<String> lc = new LinkedContainer<>();

    @Before
    public void setUp() {
        lc.addLast("one");
        lc.addLast("two");
        lc.addLast("three");
        lc.addLast("four");
        lc.addLast("five");
    }

    @Test
    public void whenAddLastThenAddedInTheEnd() {
        lc.addLast("TEST");

        assertThat(lc.getElementByIndex(5), is("TEST"));
    }

    @Test
    public void whenAddFisrtThenAddedInTheBegin() {
        lc.addFirst("TEST");

        assertThat(lc.getElementByIndex(0), is("TEST"));
    }

    @Test
    public void whenListFilledThenReturnSize() {
        assertThat(lc.size(), is(5));

        lc.addFirst("TEST");

        assertThat(lc.size(), is(6));
    }

    @Test
    public void whenSearchByIndexThenReturnRightItem() {
        assertThat(lc.getElementByIndex(0), is("one"));
        assertThat(lc.getElementByIndex(1), is("two"));
        assertThat(lc.getElementByIndex(2), is("three"));
        assertThat(lc.getElementByIndex(3), is("four"));
        assertThat(lc.getElementByIndex(4), is("five"));
    }

    @Test (expected = NoSuchElementException.class)
    public void iterator() {
        Iterator iterator = lc.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("five"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();

    }

    @Test (expected = NoSuchElementException.class)
    public void descendingIterator() {
        Iterator iterator = lc.descendingIterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("five"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test
    public void whenRemoveLastThenLastIsPrevious() {
        lc.removeLast();

        assertThat(lc.getElementByIndex(0), is("one"));
        assertThat(lc.getElementByIndex(1), is("two"));
        assertThat(lc.getElementByIndex(2), is("three"));
        assertThat(lc.getElementByIndex(3), is("four"));
        assertThat(lc.size(), is(4));
        assertTrue(lc.getElementByIndex(4) == null);
    }

    @Test
    public void whenRemoveFirstThenSecondBecomesFirst() {
        lc.removeFirst();

        assertThat(lc.getElementByIndex(0), is("two"));
        assertThat(lc.getElementByIndex(1), is("three"));
        assertThat(lc.getElementByIndex(2), is("four"));
        assertThat(lc.getElementByIndex(3), is("five"));
        assertThat(lc.size(), is(4));
    }
}