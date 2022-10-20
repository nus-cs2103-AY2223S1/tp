package seedu.address.model.module;

import java.time.LocalTime;

/**
 * Lecture slots
 */
public class Lecture extends Lesson {

    private static String type = "lec";

    /**
     * Constructor for lecture
     *
     * @param module code for module
     * @param day of week, 1 for monday, 7 for sunday
     * @param startTime time lecture starts
     * @param endTime time lecture ends
     */
    public Lecture(String module, int day, LocalTime startTime, LocalTime endTime) {
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
