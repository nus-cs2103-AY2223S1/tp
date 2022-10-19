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
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Todo}. All parameters must be present and not null.
     */
    public Todo(Description description, Priority priority, Set<Tag> tags) {
        requireAllNonNull(description, priority, tags);
        this.description = description;
        this.priority = priority;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return this.description;
    }

    public String getDatetime() {
        return "Datetime";
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
                && otherTodo.getPriority().equals(priority)
                && otherTodo.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, priority, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
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
