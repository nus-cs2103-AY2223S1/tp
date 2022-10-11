package seedu.address.model.tuitionclass;

import java.time.LocalTime;

/**
 * Represents the timeslot of the tuition class.
 */
public class Time {

    private final LocalTime startTime;
    private final LocalTime endTime;

    public static final String MESSAGE_CONSTRAINTS =
            "Starting time and ending time must be present in HH:MM and separated by a dash";
    public static final String VALIDATION_REGEX = "\\p{Digit}{2}:\\p{Digit}{2}-\\p{Digit}{2}:\\p{Digit}{2}";
    public final String timeFrame;


    /**
     * The constructor for a Time object.
     *
     * @param startTime A string representing the start time of the timeslot of a tuition class.
     * @param endTime A string representing the end time of the timeslot of a tuition class.
     */
    public Time(String startTime, String endTime) {
        this.timeFrame = startTime + "-" + endTime;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }


    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
