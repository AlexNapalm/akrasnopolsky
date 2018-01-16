package ru.job4j.events;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EventRegistrarTest {

    @Test
    public void whenAddEventsThenReturnRightNumberOfEvents() {
        EventRegistrar er = new EventRegistrar();

        er.eventPerformed();
        er.eventPerformed();
        er.eventPerformed();
        er.eventPerformed();
        er.eventPerformed();

        assertThat(er.getEvents(1), is(5));
    }
}