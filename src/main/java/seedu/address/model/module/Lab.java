package seedu.address.model.module;

import java.time.LocalTime;

/**
 * Lab lessons.
 */
public class Lab extends Lesson {

    private static String type = "lab";

    /**
     * Constructor for lab lesson
     *
     * @param module code for module
     * @param day of week, 1 for monday, 7 for sunday
     * @param startTime time lab starts
     * @param endTime time lab ends
     */
    public Lab(String module, int day, LocalTime startTime, LocalTime endTime) {
        super(module, day, startTime, endTime);
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return type + super.toString();
    }

}
