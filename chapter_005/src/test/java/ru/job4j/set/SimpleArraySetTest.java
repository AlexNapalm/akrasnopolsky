package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArraySetTest {

    SimpleArraySet<String> sas = new SimpleArraySet<>();
    Iterator iterator = sas.iterator();

    @Before
    public void setUp() {
        sas.add("one");
        sas.add("two");
        sas.add("three");
    }

    @Test (expected = NoSuchElementException.class)
    public void whenTryAddDuplicatesDoesntAddIt() {
        sas.add("one");
        sas.add("two");
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(true));
        assertTrue(iterator.next() == null);
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }
}