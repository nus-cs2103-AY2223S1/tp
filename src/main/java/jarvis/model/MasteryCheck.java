package jarvis.model;

import java.util.Objects;

import jarvis.storage.JsonAdaptedMasteryCheck;
import jarvis.storage.JsonAdaptedTimePeriod;

/**
 * Represents a Mastery Check in JARVIS.
 * Guarantees: details are present and not null.
 */
public class MasteryCheck extends Lesson {
    /**
     * Every field must be present and not null.
     */
    public MasteryCheck(LessonDesc lessonDesc, TimePeriod timePeriod, LessonAttendance attendance, LessonNotes notes) {
        super(lessonDesc, timePeriod, attendance, notes);
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
        return otherMasteryCheck.getDesc().equals(getDesc())
                && otherMasteryCheck.startTime().equals(startTime())
                && otherMasteryCheck.endTime().equals(endTime())
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
    public String getType() {
        return "mastery check";
    }

    @Override
    public JsonAdaptedMasteryCheck toJson() {
        return new JsonAdaptedMasteryCheck(this.getDesc().lessonDesc,
                new JsonAdaptedTimePeriod(this.getTimePeriod()),
                this.getAttendance(), this.getNotes(), this.isCompleted());
    }
}
