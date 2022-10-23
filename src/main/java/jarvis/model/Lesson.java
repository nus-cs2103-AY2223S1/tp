package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Set;

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

    public Set<Student> getStudents() {
        return attendance.getAllStudents();
    }

    public String getStudentsName() {
        return attendance.getAllStudentsName();
    }

    public boolean isPresent(Student student) {
        return attendance.isPresent(student);
    }

    public void markAsPresent(Student student) {
        attendance.markAsPresent(student);
    }

    public void markAsAbsent(Student student) {
        attendance.markAsAbsent(student);
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void markAsNotCompleted() {
        isCompleted = false;
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

    public abstract LessonType getLessonType();
}
