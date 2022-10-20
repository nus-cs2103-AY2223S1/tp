package seedu.address.model.module;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Parent class for lessons to show timetable.
 */
public abstract class Lesson {

    public static final String MESSAGE_CONSTRAINTS = "Module names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String module;
    private LocalTime startTime;
    private LocalTime endTime;
    private int day;

    /**
     * Constructs new Lesson
     *
     * @param module name
     * @param day day of week i.e. monday 1, sunday 7
     * @param startTime time lesson starts
     * @param endTime time lesson ends
     */
    public Lesson(String module, int day, LocalTime startTime, LocalTime endTime) {
        this.module = module;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public abstract String getType();

    public String getModule() {
        return module;
    }

    @JsonValue
    public int getDay() {
        return day;
    }

    @JsonValue
    public LocalTime getStartTime() {
        return startTime;
    }

    @JsonValue
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidLesson(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lesson // instanceof handles nulls
                && module.equals(((Lesson) other).module)); // state check
    }

    @Override
    public int hashCode() {
        return module.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return module + day + startTime + endTime;
    }
}
