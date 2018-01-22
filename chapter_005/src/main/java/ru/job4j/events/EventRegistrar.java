package ru.job4j.events;

import java.util.HashMap;
import java.util.Map;

public class EventRegistrar implements EventCounter, EventFilter {

    private static final int MILLIS_IN_MINUTE = 60000;
    /**
     * Collection of events, where event timestamp is used as a key.
     */
    private Map<Long, Integer> events;
    /**
     * Element counter used as its "index".
     */
    private int count = 0;

    /**
     * Constructs event registrar.
     */
    public EventRegistrar() {
        this.events = new HashMap<>();
    }

    /**
     * Simulates an event and writes it to the container.
     */
    @Override
    public void eventPerformed() {
        long value = System.currentTimeMillis();
        events.put(value, count++);
    }

    /**
     * Gets number of events performed during time period.
     * @param timePeriod time period in minutes.
     * @return number of events for the period of time.
     */
    @Override
    public int getEvents(int timePeriod) {
        long currentTime = System.currentTimeMillis();
        long period = timePeriod * MILLIS_IN_MINUTE;
        long range = currentTime - period;
        int result = events.size() - events.get(range);

        return result;
    }
}
