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
        return super.getModule() + " " + typeToString() + " " + super.getStartTime() + " to " + super.getEndTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof Lecture) {
            Lecture lecture = (Lecture) o;
            boolean sameName = this.getModule().equals(lecture.getModule());
            boolean sameStart = this.getStartTime().equals(lecture.getStartTime());
            boolean sameEnd = this.getEndTime().equals(lecture.getEndTime());
            boolean sameDay = this.getDay() == lecture.getDay();
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
