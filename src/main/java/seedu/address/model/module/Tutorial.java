package seedu.address.model.module;

import java.time.LocalTime;

/**
 * Tutorial lesson
 */
public class Tutorial extends Lesson {

    private static String type = "tut";

    /**
     * Constructor for tut lesson
     *
     * @param module code for module
     * @param day of week, 1 for monday, 7 for sunday
     * @param startTime time tut starts
     * @param endTime time tut ends
     */
    public Tutorial(String module, int day, LocalTime startTime, LocalTime endTime) {
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
