package jarvis.model;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a Consult in JARVIS.
 * Guarantees: details are present and not null.
 */
public class Consult extends Lesson {
    /**
     * Every field must be present and not null.
     */
    public Consult(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students) {
        super(lessonDesc, timePeriod, students);
    }

    public Consult(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students,
                   LessonAttendance attendance, LessonNotes notes) {
        super(lessonDesc, timePeriod, students, attendance, notes);
    }

    /**
     * Returns true if both Consults have the same description,
     * occur at the same time and are attended by the same students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consult)) {
            return false;
        }

        Consult otherConsult = (Consult) other;

        boolean consultDescEquality;
        if (hasDesc()) {
            consultDescEquality = getDesc().equals(otherConsult.getDesc());
        } else {
            consultDescEquality = otherConsult.getDesc() == null;
        }
        return consultDescEquality
                && otherConsult.startDateTime().equals(startDateTime())
                && otherConsult.endDateTime().equals(endDateTime())
                && otherConsult.getAttendance().equals(getAttendance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDesc(), getTimePeriod(), getAttendance());
    }

    @Override
    public String toString() {
        return "Consult at " + getTimePeriod();
    }

    @Override
    public LessonType getLessonType() {
        return LessonType.CONSULT;
    }
}
