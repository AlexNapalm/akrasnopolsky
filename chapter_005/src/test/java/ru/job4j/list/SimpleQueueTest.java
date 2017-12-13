package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {
    SimpleQueue<String> queue = new SimpleQueue<>();

    @Before
    public void setUp() {
        queue.push("one");
        queue.push("two");
        queue.push("three");
    }

    @Test
    public void whenPollThenReturnTheHeadElement() {
        assertThat(queue.poll(), is("one"));
        assertThat(queue.size(), is(2));
        assertThat(queue.poll(), is("two"));
        assertThat(queue.size(), is(1));
        assertThat(queue.poll(), is("three"));
        assertThat(queue.size(), is(0));
    }

    @Test
    public void whenPushThenAddToContainer() {
        queue.push("TEST");

        assertThat(queue.size(), is(4));
        assertThat(queue.poll(), is("one"));
        assertThat(queue.size(), is(3));
    }

}