package ru.job4j.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsCounterTest {

    WordsCounter wc = new WordsCounter();
    String source = "this is the test text";

    @Test
    public void whenGivenTextThenReturnNumberOfWords() {
        int expected = 5;
        int result = wc.wordCounter(source);

        assertThat(result, is(expected));

        source = "this is the bigger test text with much more spaces";
        expected = 10;
        result = wc.wordCounter(source);

        assertThat(result, is(expected));
    }
}