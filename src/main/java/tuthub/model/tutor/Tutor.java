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
    private final Set<Module> modules = new HashSet<>();
    private final Year year;
    private final CommentList comments;
    private final TeachingNomination teachingNomination;
    private final Rating rating;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Phone phone, Email email, Set<Module> modules, Year year,
                    StudentId studentId, CommentList comments, TeachingNomination teachingNomination, Rating rating,
                    Set<Tag> tags) {
        requireAllNonNull(name, phone, email, modules, year, studentId, comments, teachingNomination, rating, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.modules.addAll(modules);
        this.year = year;
        this.studentId = studentId;
        this.comments = new CommentList(comments.getList());
        this.teachingNomination = teachingNomination;
        this.rating = rating;
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

    /**
     * Returns an immutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    public Year getYear() {
        return year;
    }

    public CommentList getComments() {
        return comments;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public TeachingNomination getTeachingNomination() {
        return teachingNomination;
    }

    public Rating getRating() {
        return rating;
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

        if (otherTutor != null) {
            return otherTutor.getStudentId().equals(getStudentId())
                    || otherTutor.getEmail().equals(getEmail());
        }

        return false;
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
                && otherTutor.getModules().equals(getModules())
                && otherTutor.getYear().equals(getYear())
                && otherTutor.getStudentId().equals(getStudentId())
                && otherTutor.getComments().equals(getComments())
                && otherTutor.getTeachingNomination().equals(getTeachingNomination())
                && otherTutor.getRating().equals(getRating())
                && otherTutor.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, modules, year, comments, teachingNomination, rating, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Module(s): ");
        getModules().forEach(builder::append);

        builder.append("; Year: ")
                .append(getYear())
                .append("; Student ID: ")
                .append(getStudentId())
                .append("; Teaching nominations: ")
                .append(getTeachingNomination())
                .append("; Rating: ")
                .append(getRating());

        if (!comments.isEmpty()) {
            builder.append("; Comments: ")
                   .append(getComments());
        }

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
            builder.append(";");
        }
        return builder.toString();
    }

}
