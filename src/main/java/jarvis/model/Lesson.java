package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Set;

import jarvis.commons.core.index.Index;

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
        requireAllNonNull(timePeriod, attendance, notes);
        this.lessonDesc = lessonDesc;
        this.timePeriod = timePeriod;
        this.attendance = attendance;
        this.notes = notes;
    }

    public LocalDateTime startDateTime() {
        return timePeriod.getStart();
    }

    public LocalDateTime endDateTime() {
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

    public String isPresent(Student student) {
        return attendance.isPresent(student) ? "Present" : "Absent";
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

    public boolean hasDesc() {
        return lessonDesc != null;
    }

    public LessonDesc getDesc() {
        return hasDesc() ? lessonDesc : null;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public LessonAttendance getAttendance() {
        return attendance;
    }

    public String getStudentNotes(Student student) {
        return notes.getStudentNotes(student);
    }

    public String getGeneralNotes() {
        return notes.getGeneralNotes();
    }

    public abstract LessonType getLessonType();

    public void addOverallNote(String overallNotes) {
        notes.addNote(overallNotes);
    }

    public void addStudentNote(String studentNotes, Student student) {
        notes.addNote(student, studentNotes);
    }

    public String deleteOverallNote(Index index) {
        return notes.deleteNote(index.getZeroBalistaddnote n/Get back to jeff on streams li/1sed());
    }

    public String deleteStudentNote(Student student, Index index) {
        return notes.deleteNote(student, index.getZeroBased());
    }
}
