package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {
    SimpleStack<String> stack = new SimpleStack();

    @Before
    public void setUp() {
        stack.push("one");
        stack.push("two");
        stack.push("three");
    }

    @Test
    public void whenPopThenReturnsLastAddedElementAndRemovesItFromContainer() {
        assertThat(stack.pop(), is("three"));
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is("two"));
        assertThat(stack.size(), is(1));
        assertThat(stack.pop(), is("one"));
        assertThat(stack.size(), is(0));
    }

    @Test
    public void whenPushThenPopThisElement() {
        stack.push("TEST");

        assertThat(stack.size(), is(4));
        assertThat(stack.pop(), is("TEST"));
        assertThat(stack.size(), is(3));
    }
}