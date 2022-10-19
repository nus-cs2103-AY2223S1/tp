package seedu.address.model.module;

import java.time.LocalTime;

/**
 * Recitation lesson
 */
public class Recitation extends Lesson {

    private static String type = "rec";

    /**
     * Constructor for recitation lesson
     *
     * @param module code for module
     * @param day of week, 1 for monday, 7 for sunday
     * @param startTime time rec starts
     * @param endTime time rec ends
     */
    public Recitation(String module, int day, LocalTime startTime, LocalTime endTime) {
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
