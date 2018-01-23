package ru.job4j.events;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EventRegistrarTest {

    @Test
    public void whenAddEventsThenReturnRightNumberOfEvents() {
        EventRegistrar er = new EventRegistrar();

        for (int i = 0; i < 1_000_000; i++) {
            er.eventPerformed();
        }

        assertThat(er.getEvents(1), is(1_000_000));
    }
}