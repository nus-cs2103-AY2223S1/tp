package seedu.address.model.tuitionclass;

import java.time.LocalDate;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {

    private final LocalDate startTime;
    private final LocalDate endTime;

    /**
     * The constructor for a Time object.
     *
     * @param startTime The start time of the timeslot of a tuition class.
     * @param endTime The end time of the timeslot of a tuition class.
     */
    public Time(LocalDate startTime, LocalDate endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
