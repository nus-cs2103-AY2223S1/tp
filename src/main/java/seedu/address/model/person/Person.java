package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the task tracker.
 * Guarantees: All details are present and not null, field values are validated and immutable
 */
public class Person {

    private final Name name;
    private final Name module;
    private final LocalDate deadline;
    private final Set<Tag> tags = new HashSet<>();
    private final boolean isDone = false;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Name module, LocalDate deadline, Set<Tag> tags) {
        requireAllNonNull(name, module, deadline, tags);
        this.name = name;
        this.module = module;
        this.deadline = deadline;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Name getModule() {
        return module;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherTask = (Person) other;
        return otherTask.getName().equals(getName())
                && otherTask.getModule().equals(getModule())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getTags().equals(getTags())
                && otherTask.isDone == this.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, module, deadline, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Module: ")
                .append(getModule())
                .append("; Deadline: ")
                .append(getDeadline());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Done: ").append(isDone);
        return builder.toString();
    }
}
