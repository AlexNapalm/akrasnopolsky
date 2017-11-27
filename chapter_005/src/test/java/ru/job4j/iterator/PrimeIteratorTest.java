package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PrimeIteratorTest {

    @Test
    public void whenPrimeThenTrue() {
        PrimeIterator it = new PrimeIterator(new int[]{1});

        assertThat(it.isPrime(2), is(true));
        assertThat(it.isPrime(3), is(true));
        assertThat(it.isPrime(4), is(false));
        assertThat(it.isPrime(5), is(true));
        assertThat(it.isPrime(6), is(false));
        assertThat(it.isPrime(21), is(false));
        assertThat(it.isPrime(43), is(true));

    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnPrimeNumbersOnly() {
        PrimeIterator it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        PrimeIterator it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }

    @Test
    public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber() {
        PrimeIterator it = new PrimeIterator(new int[]{1, 4, 6});

        assertThat("should return false, cause there is no any prime number", it.hasNext(), is(false));
    }
}