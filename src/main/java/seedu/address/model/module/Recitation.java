package seedu.address.model.module;

import java.time.LocalTime;

public class Recitation extends Lesson {

    private static String type = "rec";

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
        return type + module + day + startTime + endTime;
    }

}
