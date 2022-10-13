package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final ID id;
    private final Phone phone;
    private final Email email;
    private final Telegram telegram;

    // Data fields
    private final TutorialModule tutorialModule;
    private final TutorialName tutorialName;
    //private final Grade grade;
    //private final Attendance attendance;
    //private final Participation participation;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, ID id, Phone phone, Email email,
                   Telegram telegram, TutorialModule tutorialModule, TutorialName tutorialName, Set<Tag> tags) {
        requireAllNonNull(name, id, phone, email, telegram, tutorialModule, tutorialName, tags);
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tutorialModule = tutorialModule;
        this.tutorialName = tutorialName;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public ID getId() { return id; }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public TutorialModule getTutorialModule() { return tutorialModule; }

    public TutorialName getTutorialName() { return tutorialName; }

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
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
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
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getTutorialModule().equals(getTutorialModule())
                && otherStudent.getTutorialName().equals(getTutorialName())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, telegram, tutorialModule, tutorialName, tags);
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
                .append("; Tutorial: ")
                .append(getTutorialModule())
                .append(" ")
                .append(getTutorialName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
