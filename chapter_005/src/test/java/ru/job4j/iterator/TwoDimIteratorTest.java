package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class/
 */
public class TwoDimIteratorTest {
    /**
     * getElements test.
     */
    @Test
    public void whenGetSquareArrayThenCountElements() {
        TwoDimIterator tdi = new TwoDimIterator(new int[][]{
                {1, 2, 3},
                {4, 5, 6}}
        );

        int result = tdi.getElements();

        assertThat(result, is(6));
    }

    /**
     * getElements test.
     */
    @Test
    public void whenGetMergedArrayThenGetElements() {
        TwoDimIterator tdi = new TwoDimIterator(new int[][]{
                {1, 2, 3},
                {4, 5},
                {6},
                {7, 8, 9, 10, 11}}
        );

        int result = tdi.getElements();

        assertThat(result, is(11));
    }

    /**
     * next and hasNext test.
     */
    @Test
    public void whenGetNextCallShouldPointerForward() {
        TwoDimIterator tdi = new TwoDimIterator(new int[][]{
                {1, 2, 3},
                {4, 5, 6}}
        );

        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(1));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(2));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(3));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(4));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(5));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(6));
        assertThat(tdi.hasNext(), is(false));
    }

    /**
     * next and hasNext test.
     */
    @Test
    public void whenGetNextCallInMergedArrayShouldPointerForward() {
        TwoDimIterator tdi = new TwoDimIterator(new int[][]{
                {1, 2, 3},
                {4, 5},
                {6},
                {7, 8, 9, 10, 11}}
        );

        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(1));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(2));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(3));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(4));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(5));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(6));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(7));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(8));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(9));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(10));
        assertThat(tdi.hasNext(), is(true));
        assertThat(tdi.next(), is(11));
        assertThat(tdi.hasNext(), is(false));
    }
}