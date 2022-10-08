package seedu.address.model.tuitionclass;

import java.time.LocalDate;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {

    private final LocalDate startTime;
    private final LocalDate endTime;

    public Time(LocalDate startTime, LocalDate endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
