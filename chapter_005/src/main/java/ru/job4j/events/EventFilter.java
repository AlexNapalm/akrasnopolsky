package ru.job4j.events;

public interface EventFilter {
    /**
     * Gets number of events performed during time period.
     * @param timePeriod time period in minutes.
     * @return number of events for the period of time.
     */
    int getEvents(int timePeriod);
}
