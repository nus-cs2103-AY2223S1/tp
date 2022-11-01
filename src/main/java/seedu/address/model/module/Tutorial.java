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
        return super.getModule() + " " + typeToString() + " " + super.getStartTime() + " to " + super.getEndTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof Tutorial) {
            Tutorial tutorial = (Tutorial) o;
            boolean sameName = this.getModule().equals(tutorial.getModule());
            boolean sameStart = this.getStartTime().equals(tutorial.getStartTime());
            boolean sameEnd = this.getEndTime().equals(tutorial.getEndTime());
            boolean sameDay = this.getDay() == tutorial.getDay();
            return sameName && sameStart && sameDay && sameEnd;
        }

        return false;
    }

    @Override
    public String toFullString() {
        return super.getModule() + " " + typeToString() + " on "
                + toDayString() + " " + super.getStartTime() + " to " + super.getEndTime();
    }

}
