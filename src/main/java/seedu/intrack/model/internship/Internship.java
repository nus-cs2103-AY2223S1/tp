package seedu.intrack.model.internship;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.intrack.model.tag.Tag;

/**
 * Represents an Internship in the internship tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    // Identity fields
    private final Name name;
    private final Position position;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Status status;
    private final Website website;
    private final List<Task> tasks = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;

    /**
     * Every field must be present and not null.
     */

    public Internship(Name name, Position position, Status status, Phone phone, Email email, Website website,
                      List<Task> tasks, Set<Tag> tags, Remark remark) {
        requireAllNonNull(name, position, phone, email, status, website, tasks, tags, remark);
        this.name = name;
        this.position = position;
        this.status = status;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.tasks.addAll(tasks);
        this.tags.addAll(tags);
        this.remark = remark;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Website getWebsite() {
        return website;
    }

    /**
     * Returns an immutable task list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns true if both internships have the same name.
     * This defines a weaker notion of equality between two internships.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getName().equals(getName())
                && otherInternship.getPosition().equals(getPosition());
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return otherInternship.getName().equals(getName())
                && otherInternship.getPosition().equals(getPosition())
                && otherInternship.getStatus().equals(getStatus())
                && otherInternship.getPhone().equals(getPhone())
                && otherInternship.getEmail().equals(getEmail())
                && otherInternship.getWebsite().equals(getWebsite())
                && otherInternship.getTasks().equals(getTasks())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, phone, email, website, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(getName())
                .append("; Position: ")
                .append(getPosition())
                .append("; Status: ")
                .append(getStatus())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Website: ")
                .append(getWebsite());

        List<Task> tasks = getTasks();
        if (!tasks.isEmpty()) {
            builder.append("; Tasks: ");
            tasks.forEach(builder::append);
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append(" Remark: ")
                .append(getRemark());

        return builder.toString();
    }

}
