package seedu.address.model.tuitionclass;

import java.time.LocalTime;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {

    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * The constructor for a Time object.
     *
     * @param startTime A string representing the start time of the timeslot of a tuition class.
     * @param endTime A string representing the end time of the timeslot of a tuition class.
     */
    public Time(String startTime, String endTime) {
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }
}
