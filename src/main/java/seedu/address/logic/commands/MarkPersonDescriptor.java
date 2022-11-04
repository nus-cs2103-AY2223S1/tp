package seedu.address.logic.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Homework;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to mark/unmark the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class MarkPersonDescriptor {
    private Name name;
    private Phone phone;
    private LessonPlan lessonPlan;
    private Index homeworkIndex;
    private Homework homework;
    private Index gradeProgressIndex;
    private GradeProgress gradeProgress;
    private Index attendanceIndex;
    private Attendance attendance;
    private Index sessionIndex;
    private Session session;
    private Set<Tag> tags;

    public MarkPersonDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public MarkPersonDescriptor(MarkPersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setLessonPlan(toCopy.lessonPlan);
        setHomeworkIndex(toCopy.homeworkIndex);
        setHomework(toCopy.homework);
        setGradeProgressIndex(toCopy.gradeProgressIndex);
        setGradeProgress(toCopy.gradeProgress);
        setAttendanceIndex(toCopy.attendanceIndex);
        setAttendance(toCopy.attendance);
        setSessionIndex(toCopy.sessionIndex);
        setSession(toCopy.session);
        setTags(toCopy.tags);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setLessonPlan(LessonPlan lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    public Optional<LessonPlan> getLessonPlan() {
        return Optional.ofNullable(lessonPlan);
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Optional<Homework> getHomework() {
        return Optional.ofNullable(homework);
    }

    public void setHomeworkIndex(Index homeworkIndex) {
        this.homeworkIndex = homeworkIndex;
    }

    public Optional<Index> getHomeworkIndex() {
        return Optional.ofNullable(homeworkIndex);
    }

    public void setGradeProgress(GradeProgress gradeProgress) {
        this.gradeProgress = gradeProgress;
    }

    public Optional<GradeProgress> getGradeProgress() {
        return Optional.ofNullable(gradeProgress);
    }

    public void setGradeProgressIndex(Index gradeProgressIndex) {
        this.gradeProgressIndex = gradeProgressIndex;
    }

    public Optional<Index> getGradeProgressIndex() {
        return Optional.ofNullable(gradeProgressIndex);
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Optional<Attendance> getAttendance() {
        return Optional.ofNullable(attendance);
    }

    public void setAttendanceIndex(Index attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }

    public Optional<Index> getAttendanceIndex() {
        return Optional.ofNullable(attendanceIndex);
    }

    public Optional<Session> getSession() {
        return Optional.ofNullable(session);
    }

    public Optional<Index> getSessionIndex() {
        return Optional.ofNullable(sessionIndex);
    }

    public void setSessionIndex(Index sessionIndex) {
        this.sessionIndex = sessionIndex;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // for same object
        if (other == this) {
            return true;
        }

        // checks for null object
        if (!(other instanceof MarkPersonDescriptor)) {
            return false;
        }

        // check for same values in fields
        MarkPersonDescriptor m = (MarkPersonDescriptor) other;
        return getHomework().equals(m.getHomework())
                && getHomeworkIndex().equals(m.getHomeworkIndex())
                && getAttendance().equals(m.getAttendance())
                && getAttendanceIndex().equals(m.getAttendanceIndex());
    }
}
