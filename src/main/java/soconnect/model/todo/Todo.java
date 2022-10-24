package soconnect.model.todo;

import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import soconnect.model.tag.Tag;

/**
 * Represents a {@code Todo} in the SoConnect.
 */
public class Todo {
    private final Description description;
    private final Date date;
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Todo}. All parameters must be present and not null.
     */
    public Todo(Description description, Date date, Priority priority, Set<Tag> tags) {
        requireAllNonNull(description, date, priority, tags);
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns an immutable {@code Tag} set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks if the {@code Todo} has the {@code tag}.
     *
     * @param tag The {@code Tag} to be added.
     * @return True if {@code tag} already exists. False if otherwise.
     */
    public boolean contains(Tag tag) {
        return this.getTags().contains(tag);
    }


    /**
     * Checks if this {@code Date} is after the specified date.
     *
     * @param other The other {@code Date} to compare to.
     * @return True if this date is after the specified date.
     */
    public boolean isAfter(Date other) {
        return this.date.isAfter(other);
    }

    /**
     * Checks if this {@code Date} is before the specified date.
     *
     * @param other The other {@code Date} to compare to.
     * @return True if this date is after the specified date.
     */
    public boolean isBefore(Date other) {
        return this.date.isBefore(other);
    }

    /**
     * Checks if the {@code Todo} is within the given date range.
     *
     * @param startDate The start date of the date range.
     * @param endDate The end date of the date range.
     * @return True if the {@code Todo} is within the given date range.
     */
    public boolean isWithinDateRange(Date startDate, Date endDate) {
        return this.date.isWithinDateRange(startDate, endDate);
    }

    /**
     * Compares this {@code Todo} to another {@code Todo}.
     * This will first compare by {@code Date}, if they are the same then compare by {@code Priority}.
     * A {@code Todo} is lesser than the other {@code Todo} if it's {@code Date} is earlier than
     * the other {@code Todo}. If they have same {@code Date}, then the {@code Todo} is lesser if it's
     * {@code Priority} ranking is lower.
     *
     * @param other The other {@code Todo}.
     * @return Negative if this {@code Todo} is lesser, 0 if they are equal, positive otherwise.
     */
    public int compareTo(Todo other) {
        int dateComparedValue = this.date.compareTo(other.date);

        if (dateComparedValue != 0) {
            return dateComparedValue;
        }

        return this.priority.compareTo(other.priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getDescription().equals(description)
                && otherTodo.getDate().equals(date)
                && otherTodo.getPriority().equals(priority)
                && otherTodo.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, date, priority, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Date: ")
                .append(getDate())
                .append("; Priority: ")
                .append(getPriority());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
