package jarvis.model;

import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Studio in JARVIS.
 * Guarantees: details are present and not null.
 */
public class Studio extends Lesson {
    private final StudioParticipation participation;

    /**
     * Every field must be present and not null.
     */
    public Studio(LessonDesc lessonDesc, TimePeriod timePeriod, LessonAttendance attendance,
                  StudioParticipation participation, LessonNotes notes) {
        super(lessonDesc, timePeriod, attendance, notes);
        requireAllNonNull(participation);
        this.participation = participation;
    }

    public void setParticipationForStudent(Student student, int i) {
        participation.setParticipationForStudent(student, i);
    }

    public int getParticipationForStudent(Student student) {
        return participation.getParticipationForStudent(student);
    }

    public StudioParticipation getParticipation() {
        return participation;
    }

    /**
     * Returns true if both Studios have the same description,
     * occur at the same time, are attended by the same students,
     * and all students have the same participation scores.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Studio)) {
            return false;
        }

        Studio otherStudio = (Studio) other;
        return otherStudio.getDesc().equals(getDesc())
                && otherStudio.startTime().equals(startTime())
                && otherStudio.endTime().equals(endTime())
                && otherStudio.getAttendance().equals(getAttendance())
                && otherStudio.getParticipation().equals(getParticipation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDesc(), getTimePeriod(), getAttendance(), getParticipation());
    }

    @Override
    public String toString() {
        return "Studio at " + getTimePeriod();
    }

    @Override
    public LessonType getLessonType() {
        return LessonType.STUDIO;
    }
}
