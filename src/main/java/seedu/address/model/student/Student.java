package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commons.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final StudentId id;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Data fields
    private final ModuleCode moduleCode;
    private final TutorialName tutorialName;
    private final Grade grade;
    private final Attendance attendance;
    private final Participation participation;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentId id, Phone phone, Email email,
                   TelegramHandle telegramHandle, ModuleCode moduleCode,
                   TutorialName tutorialName, Attendance attendance,
                   Participation participation, Grade grade, Set<Tag> tags) {
        requireAllNonNull(name, id, phone, email, telegramHandle, moduleCode, tutorialName,
                attendance, participation, grade, tags);
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.moduleCode = moduleCode;
        this.tutorialName = tutorialName;
        this.attendance = attendance;
        this.participation = participation;
        this.grade = grade;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public StudentId getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getTelegram() {
        return telegramHandle;
    }

    public ModuleCode getTutorialModule() {
        return moduleCode;
    }

    public TutorialName getTutorialName() {
        return tutorialName;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Participation getParticipation() {
        return participation;
    }

    public Grade getGrade() {
        return grade;
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
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getId().equals(getId());
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

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getId().equals(getId())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getTutorialModule().equals(getTutorialModule())
                && otherStudent.getTutorialName().equals(getTutorialName())
                && otherStudent.getAttendance().equals(getAttendance())
                && otherStudent.getParticipation().equals(getParticipation())
                && otherStudent.getGrade().equals(getGrade())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, telegramHandle, moduleCode, tutorialName,
                attendance, participation, grade, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; ID: ")
                .append(getId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Telegram Handle: ")
                .append(getTelegram())
                .append("; Module: ")
                .append(getTutorialModule())
                .append("; Tutorial: ")
                .append(getTutorialName())
                .append("; Attendance: ")
                .append(getAttendance())
                .append("; Participation: ")
                .append(getParticipation())
                .append("; Grade: ")
                .append(getGrade());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
