package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import jarvis.commons.exceptions.IllegalValueException;

/**
 * Represents a Lesson in JARVIS.
 * Guarantees: details are present and not null.
 */
public abstract class Lesson {

    // Identity fields
    private final LessonDesc lessonDesc;
    private final TimePeriod timePeriod;

    // Data fields
    private final LessonAttendance attendance;
    private final LessonNotes notes;
    private boolean isCompleted = false;

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonDesc lessonDesc, TimePeriod timePeriod, LessonAttendance attendance, LessonNotes notes) {
        requireAllNonNull(lessonDesc, timePeriod, attendance, notes);
        this.lessonDesc = lessonDesc;
        this.timePeriod = timePeriod;
        this.attendance = attendance;
        this.notes = notes;
    }

    public LocalDateTime startTime() {
        return timePeriod.getStart();
    }

    public LocalDateTime endTime() {
        return timePeriod.getEnd();
    }

    public boolean hasTimingConflict(Lesson other) {
        return timePeriod.hasOverlap(other.timePeriod);
    }

    public boolean isPresent(Student student) {
        return attendance.isPresent(student);
    }

    public void markAsPresent(Student student) throws IllegalValueException {
        attendance.markAsPresent(student);
    }

    public void markAsAbsent(Student student) throws IllegalValueException {
        attendance.markAsAbsent(student);
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    public LessonDesc getDesc() {
        return lessonDesc;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public LessonAttendance getAttendance() {
        return attendance;
    }

    /**
     * Returns true if both lessons occur at the same time and are attended
     * by the same students.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getDesc().equals(getDesc())
                && otherLesson.getTimePeriod().equals(getTimePeriod())
                && otherLesson.getAttendance().equals(getAttendance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonDesc, timePeriod);
    }

    @Override
    public String toString() {
        return "Lesson at " + timePeriod + ": " + getDesc();
    }

}
