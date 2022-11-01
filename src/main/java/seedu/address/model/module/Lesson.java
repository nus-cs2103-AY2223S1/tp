package seedu.address.model.module;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
        requireAllNonNull(module, day, startTime, endTime);
        checkArgument(isValidLesson(module), MESSAGE_CONSTRAINTS);
        this.module = module.toUpperCase();
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
     * @return the String value of the lesson type (instead of abbreviation).
     */
    public String typeToString() {
        switch (getType()) {
        case "tut":
            return "Tutorial";
        case "lab":
            return "Lab";
        case "lec":
            return "Lecture";
        case "rec":
            return "Recitation";
        default:
            return "Unknown lesson type";
        }
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
        return module + " " + startTime + " to " + endTime;
    }

    /**
     * @return String with day to successful message.
     */
    public abstract String toFullString();

    /**
     * Converts day from int to String
     *
     * @return String of day e.g. Monday
     */
    public String toDayString() {
        switch(day) {
        case 1:
            return "Monday";
        case 2:
            return "Tuesday";
        case 3:
            return "Wednesday";
        case 4:
            return "Thursday";
        case 5:
            return "Friday";
        case 6:
            return "Saturday";
        case 7:
            return "Sunday";
        default:
            return "unknown day";
        }
    }
}
