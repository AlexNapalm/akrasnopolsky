package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 3, 5, 13, 27, 4, 6, 7, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 4, 3, 7, 6, 8, 9, 11});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(8));
        it.next();
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        EvenIterator it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

}