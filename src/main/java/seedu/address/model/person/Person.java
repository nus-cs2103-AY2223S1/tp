package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    //boolean toggle for fullView format.
    private boolean isFullView;
    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final LessonPlan lessonPlan;
    private final HomeworkList homeworkList;
    private final AttendanceList attendanceList;
    private final GradeProgressList gradeProgressList;
    private final SessionList sessionList;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, LessonPlan lessonPlan,
                  HomeworkList homeworkList, AttendanceList attendanceList,
                  SessionList sessionList, GradeProgressList gradeProgressList,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, tags);
        this.isFullView = false;
        this.name = name;
        this.phone = phone;
        this.lessonPlan = lessonPlan;
        this.homeworkList = homeworkList;
        this.attendanceList = attendanceList;
        this.sessionList = sessionList;
        this.gradeProgressList = gradeProgressList;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public LessonPlan getLessonPlan() {
        return lessonPlan;
    }

    public HomeworkList getHomeworkList() {
        return homeworkList;
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public SessionList getSessionList() {
        return sessionList;
    }

    public List<String> getSessionDays() {
        return sessionList.getDays();
    }

    public GradeProgressList getGradeProgressList() {
        return gradeProgressList;
    }

    /**
     * Sets the person's details to be listed in full.
     */
    public void setFullView() {
        isFullView = true;
    }

    /**
     * Sets the person's details to be truncated in list mode.
     */
    public void setListView() {
        isFullView = false;
    }

    /**
     * Returns whether the person's details are fully listed.
     */
    public boolean isFullView() {
        return isFullView;
    }

    /**
     * Clears the person's attendance list.
     */
    public void clearAttendanceList() {
        attendanceList.clearList();
    }

    public void clearSessionList() {
        sessionList.clearList();
    }
    /**
     * Clears the person's homework list.
     */
    public void clearHomeworkList() {
        homeworkList.clearList();
    }

    /**
     * Clears the person's Grade progress list.
     */
    public void clearGradeProgressList() {
        gradeProgressList.clearList();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getLessonPlan().equals(getLessonPlan())
                && otherPerson.getHomeworkList().equals(getHomeworkList())
                && otherPerson.getAttendanceList().equals(getAttendanceList())
                && otherPerson.getSessionList().equals(getSessionList())
                && otherPerson.getGradeProgressList().equals(getGradeProgressList())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, lessonPlan, homeworkList,
                attendanceList, sessionList, gradeProgressList, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Lesson Plan: ")
                .append(getLessonPlan())
                .append("; Homework: ")
                .append(getHomeworkList())
                .append("; Attendance: ")
                .append(getAttendanceList())
                .append("; Session: ")
                .append(getSessionList())
                .append("; Grade Progress: ")
                .append(getGradeProgressList());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
