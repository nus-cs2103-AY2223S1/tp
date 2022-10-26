package jarvis.model;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a Mastery Check in JARVIS.
 * Guarantees: details are present and not null.
 */
public class MasteryCheck extends Lesson {
    /**
     * Every field must be present and not null.
     */
    public MasteryCheck(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students) {
        super(lessonDesc, timePeriod, students);
    }

    public MasteryCheck(LessonDesc lessonDesc, TimePeriod timePeriod, Collection<Student> students,
                        LessonAttendance attendance, LessonNotes notes) {
        super(lessonDesc, timePeriod, students, attendance, notes);
    }

    /**
     * Returns true if both MasteryChecks have the same description,
     * occur at the same time and are attended by the same students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MasteryCheck)) {
            return false;
        }

        MasteryCheck otherMasteryCheck = (MasteryCheck) other;

        boolean masteryCheckDescEquality;
        if (hasDesc()) {
            masteryCheckDescEquality = getDesc().equals(otherMasteryCheck.getDesc());
        } else {
            masteryCheckDescEquality = otherMasteryCheck.getDesc() == null;
        }

        return masteryCheckDescEquality
                && otherMasteryCheck.startDateTime().equals(startDateTime())
                && otherMasteryCheck.endDateTime().equals(endDateTime())
                && otherMasteryCheck.getAttendance().equals(getAttendance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDesc(), getTimePeriod(), getAttendance());
    }

    @Override
    public String toString() {
        return "Mastery Check at " + getTimePeriod();
    }

    @Override
    public LessonType getLessonType() {
        return LessonType.MASTERY_CHECK;
    }
}
