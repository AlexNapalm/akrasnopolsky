package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {
    /**
     * calculatePosition test.
     */
    @Test
    public void shouldReturnFirstEvenNumberPosition() {
        int[] values = new int[] {1, 3, 5, 2, 4, 8};
        EvenIterator it = new EvenIterator(values);
        int result = it.calculatePosition(values);

        assertThat(result, is(3));

        values = new int[] {2, 3, 4, 5, 6};
        result = it.calculatePosition(values);

        assertThat(result, is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 3, 4, 6, 7, 8});
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

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 3, 4, 6, 7, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(8));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        EvenIterator it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

}