package seedu.address.model.task;

import static java.util.Objects.hash;

/**
 * Represents the id of a task in the task list.
 */
public class Id implements Comparable<Id> {
    private static int availableId;
    private static boolean hasAvailableIdBeenInitialized = false;
    private static int largestExistingId = 0;

    public final int value;

    /**
     * Constructs a new {@code Id} with a new id value.
     */
    public Id() {
        if (!hasAvailableIdBeenInitialized) {
            resetAvailableId();
        }
        value = availableId;
        availableId++;
    }

    /**
     * Constructs a new {@code Id} with an existing id value.
     *
     * @param id existing id.
     */
    public Id(int id) {
        value = id;
        if (id > largestExistingId) {
            largestExistingId = id;
        }
    }

    /**
     * Resets available id to the largest existing id + 1.
     * This is needed as information about available ids are not stored.
     */
    private static void resetAvailableId() {
        availableId = largestExistingId + 1;
        hasAvailableIdBeenInitialized = true;
    }

    /**
     * Returns an id that has not been used yet.
     *
     * @return Available id.
     */
    public static int getAvailableId() {
        return availableId;
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
