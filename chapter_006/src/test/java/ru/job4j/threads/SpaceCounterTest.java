package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SpaceCounterTest {
    SpaceCounter sc = new SpaceCounter();
    String source = "this is the test text";

    @Test
    public void whenGivenTextThenReturnNumberOfSpaces() {
        int expected = 4;
        int result = sc.spaceCounter(source);

        assertThat(result, is(expected));

        source = "this is the bigger test text with much more spaces";
        expected = 9;
        result = sc.spaceCounter(source);

        assertThat(result, is(expected));
    }
}