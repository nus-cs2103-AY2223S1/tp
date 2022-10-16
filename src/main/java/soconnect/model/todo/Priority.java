package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Todo's priority in the TodoList.
 * Can only be one of three values: LOW, MEDIUM, or HIGH.
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
        "Priority can only be LOW, MEDIUM, or HIGH";

    public static final String PRIORITY_LOW = "LOW";
    public static final String PRIORITY_MEDIUM = "MEDIUM";
    public static final String PRIORITY_HIGH = "HIGH";

    /**
     * Maps the 3 priorities to their ranking in the TodoList.
     * {@code PRIORITY_HIGH} has the highest ranking and {@code PRIORITY_LOW} has the lowest ranking.
     */
    public static final Map<String, Integer> ranking = new HashMap<>();

    public final String priority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = priority;
        ranking.put(PRIORITY_LOW, 3);
        ranking.put(PRIORITY_MEDIUM, 2);
        ranking.put(PRIORITY_HIGH, 1);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals(PRIORITY_LOW) || (test.equals(PRIORITY_MEDIUM) || test.equals(PRIORITY_HIGH));
    }


    @Override
    public String toString() {
        return priority;
    }

    /**
     * Compares this Priority to another Priority.
     *
     * @param other The other Priority object
     * @return      -1 if this object is lesser, 0 if they are equal, 1 otherwise
     */
    public int compareTo(Priority other) {
        Integer thisRanking = ranking.get(this.priority);
        Integer otherRanking = ranking.get(other.priority);
        return thisRanking.compareTo(otherRanking);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Priority // instanceof handles nulls
            && priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }

}
