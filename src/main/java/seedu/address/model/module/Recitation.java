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
        return super.getModule() + " " + typeToString() + " " + super.getStartTime() + " to " + super.getEndTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof Recitation) {
            Recitation recitation = (Recitation) o;
            boolean sameName = this.getModule().equals(recitation.getModule());
            boolean sameStart = this.getStartTime().equals(recitation.getStartTime());
            boolean sameEnd = this.getEndTime().equals(recitation.getEndTime());
            boolean sameDay = this.getDay() == recitation.getDay();
            return sameName && sameStart && sameDay && sameEnd;
        }

        return false;
    }

    @Override
    public String toFullString() {
        return super.getModule() + " " + typeToString() + " on " + toDayString()  + " " + super.getStartTime() + " to " + super.getEndTime();
    }

}
