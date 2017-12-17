package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {
    SimpleLinkedSet<String> sls = new SimpleLinkedSet<>();

    @Before
    public void setUp() {
        sls.add("one");
        sls.add("two");
        sls.add("three");
    }

    @Test
    public void whenTryToAddDuplicateDoesntAddIt() {
        sls.add("one");
        sls.add("two");
        sls.add("four");

        assertThat(sls.getElementByIndex(0), is("one"));
        assertThat(sls.getElementByIndex(1), is("two"));
        assertThat(sls.getElementByIndex(2), is("three"));
        assertThat(sls.getElementByIndex(3), is("four"));
        assertTrue(sls.getElementByIndex(4) == null);
        assertThat(sls.size(), is(4));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorTest() {
        Iterator it = sls.iterator();
        sls.add("one");
        sls.add("two");
        sls.add("four");

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("three"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("four"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}