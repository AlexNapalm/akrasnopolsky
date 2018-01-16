package ru.job4j.events;

import java.util.Deque;
import java.util.LinkedList;

public class EventRegistrar implements EventCounter, EventFilter {

    public static final int MILLIS_IN_MINUTE = 60000;
    /**
     * List of events.
     */
    private Deque<Long> events;

    /**
     * Constructs event registrar.
     */
    public EventRegistrar() {
        this.events = new LinkedList<>();
    }

    /**
     * Simulates an event and writes it to the container.
     */
    @Override
    public void eventPerformed() {
        long value = System.currentTimeMillis();
        events.addFirst(value);
    }

    /**
     * Gets number of events performed during time period.
     * @param timePeriod time period in minutes.
     * @return number of events for the period of time.
     */
    @Override
    public int getEvents(int timePeriod) {
        long currentTime = System.currentTimeMillis();
        long border = timePeriod * MILLIS_IN_MINUTE;
        long range = currentTime - border;
        int result = 0;

        for (Long element : events) {
            if (element >= range) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }
}
