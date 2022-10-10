package seedu.address.model.tuitionclass;

import java.time.LocalTime;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time should be in LocalTime parsable format";
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
    //TODO: add InvalidTimeFormatException

    /**
     * Returns the string representation of StartTime.
     */
    public String getStartTime() {
        return startTime.toString();
    }

    /**
     * Returns the string representation of EndTime.
     */
    public String getEndTime() {
        return endTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof Time) {
                Time casted = (Time) other;
                return startTime.equals(casted.startTime) && endTime.equals(casted.endTime);
            }
        }
        return false;
    }
}
