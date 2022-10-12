package tuthub.model.tutor;

import static tuthub.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import tuthub.model.tag.Tag;

/**
 * Represents a Tutor in tuthub.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutor {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final StudentId studentId;

    // Data fields
    private final Address address;
    private final Module module;
    private final Year year;
    private final Comment comment;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Phone phone, Email email, Module module, Year year,
                  StudentId studentId, Address address, Comment comment, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, module, year, address, comment, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.module = module;
        this.year = year;
        this.studentId = studentId;
        this.address = address;
        this.comment = comment;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Module getModule() {
        return module;
    }

    public Year getYear() {
        return year;
    }

    public Address getAddress() {
        return address;
    }
    public Comment getComment() {
        return comment;
    }


    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Tutors have the same name.
     * This defines a weaker notion of equality between two Tutors.
     */
    public boolean isSameTutor(Tutor otherTutor) {
        if (otherTutor == this) {
            return true;
        }

        return otherTutor != null
                && otherTutor.getName().equals(getName());
    }

    /**
     * Returns true if both Tutors have the same identity and data fields.
     * This defines a stronger notion of equality between two Tutors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutor)) {
            return false;
        }

        Tutor otherTutor = (Tutor) other;
        return otherTutor.getName().equals(getName())
                && otherTutor.getPhone().equals(getPhone())
                && otherTutor.getEmail().equals(getEmail())
                && otherTutor.getModule().equals(getModule())
                && otherTutor.getYear().equals(getYear())
                && otherTutor.getStudentId().equals(getStudentId())
                && otherTutor.getAddress().equals(getAddress())
                && otherTutor.getComment().equals(getComment())
                && otherTutor.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, module, year, address, comment, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Module: ")
                .append(getModule())
                .append("; Year: ")
                .append(getYear())
                .append("; Student ID: ")
                .append(getStudentId())
                .append(" Address: ")
                .append(getAddress())
                .append(" Comment: ")
                .append(getComment())
                .append(" Tags: ");
        getTags().forEach(builder::append);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
