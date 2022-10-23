package seedu.intrack.model.internship;

import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
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
    private final Status status;
    private final Remark remark;

    // Data fields
    private final Address address;

    private final List<Task> tasks = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    private final List<LocalDateTime> tasksDates = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */

    public Internship(Name name, Position position, Phone phone, Email email, Status status, Address address,
                      List<Task> tasks, Set<Tag> tags, Remark remark) {
        requireAllNonNull(name, position, phone, email, status, address, tasks, tags, remark);

        this.name = name;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.address = address;
        this.tasks.addAll(tasks);
        this.tags.addAll(tags);
        this.remark = remark;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            LocalDateTime x = task.getTaskTime();
            tasksDates.add(x);
        }
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
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
     * Returns the date and time of the task with the nearest date and time
     * @return LocalDateTime of the nearest date and time
     */
    public LocalDateTime getNearestTaskDate() {
        Collections.sort(tasksDates);
        LocalDateTime nearestDate = tasksDates.get(0);
        return nearestDate;
    }

    /**
     * Returns the date and time of the task with the furthest date and time
     * @return LocalDateTime of the furthest date and time
     */
    public LocalDateTime getFurthestTaskDate() {
        int maxIndex = tasks.size() - 1;
        Collections.sort(tasksDates);
        LocalDateTime furthestDate = tasksDates.get(maxIndex);
        return furthestDate;
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
                && otherInternship.getPhone().equals(getPhone())
                && otherInternship.getEmail().equals(getEmail())
                && otherInternship.getStatus().equals(getStatus())
                && otherInternship.getAddress().equals(getAddress())
                && otherInternship.getTasks().equals(getTasks())
                && otherInternship.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, position, phone, email, status, address, tasks, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(getName())
                .append("; Position: ")
                .append(getPosition())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Status: ")
                .append(getStatus())
                .append("; Address: ")
                .append(getAddress());

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
