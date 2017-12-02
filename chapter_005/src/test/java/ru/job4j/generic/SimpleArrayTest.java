package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        SimpleArray<String> simple = new SimpleArray<>(4);

        simple.add("test");
        String result = simple.get(0);

        assertThat(result, is("test"));
    }

    @Test
    public void whenTypeIntShouldReturnInt() {
        SimpleArray<Integer> simple = new SimpleArray<>(4);

        simple.add(2);
        int result = simple.get(0);

        assertThat(result, is(2));
    }

    @Test
    public void whenUpdateShouldReturnUpdatedValue() {
        SimpleArray<String> simple = new SimpleArray<>(5);

        simple.add("first value");
        simple.update(0, "second value");
        String result = simple.get(0);

        assertThat(result, is("second value"));
    }

    @Test
    public void whenDeleteShouldReturnNull() {
        SimpleArray<String> simple = new SimpleArray<>(5);

        simple.add("first");
        simple.add("second");
        simple.delete(1);
        String result = simple.get(1);

        assertTrue(result == null);
    }

    @Test
    public void whenGetShouldReturnCorrectValue() {
        SimpleArray<String> simple = new SimpleArray<>(5);

        simple.add("first");
        simple.add("second");
        String result = simple.get(0);

        assertThat(result, is("first"));
    }
}