package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {

    @Test
    public void whenAddToSetThenContainsItAndSizeGrows() {
        SimpleHashSet<String> shs = new SimpleHashSet<>();

        //test add, contains adn size methods
        assertThat(shs.add("1"), is(true));
        assertThat(shs.contains("1"), is(true));
        assertThat(shs.size(), is(1));

        shs.add("2");
        shs.add("3");
        shs.add("4");
        shs.add("5");
        shs.add("6");
        shs.add("7");
        shs.add("8");
        shs.add("9");
        shs.add("10");
        shs.add("11");
        shs.add("12");

        //check, that table grows size, when >75% is filled
        assertThat(shs.size(), is(12));
        assertThat(shs.getTableSize(), is(16));
        assertThat(shs.add("13"), is(true));
        assertThat(shs.getTableSize(), is(32));

        //test remove method
        assertThat(shs.size(), is(13));
        assertThat(shs.remove("9"), is(true));
        assertThat(shs.contains("9"), is(false));
        assertThat(shs.size(), is(12));
    }

}