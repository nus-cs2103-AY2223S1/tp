package seedu.address.model.task;

import static java.util.Objects.hash;

/**
 * Represents the id of a task in the task list.
 */
public class Id implements Comparable<Id> {
    private static int idCount = 0;
    public final int value;

    /**
     * Constructs a new {@code Id}.
     */
    public Id() {
        value = idCount;
        idCount++;
    }

    @Override
    public int compareTo(Id otherId) {
        return value - otherId.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value == ((Id) other).value); // state check
    }

    @Override
    public int hashCode() {
        return hash(value);
    }
}
