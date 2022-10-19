package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@code Todo}'s priority in the {@code TodoList}.
 * Can only be one of three values: low, medium, or high.
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS = "Priority can only be `low`, `medium`, or `high`.";

    public static final String PRIORITY_LOW = "low";
    public static final String PRIORITY_MEDIUM = "medium";
    public static final String PRIORITY_HIGH = "high";

    /**
     * Maps the 3 priorities to their ranking in the {@code TodoList}.
     * {@code PRIORITY_HIGH} has the highest ranking and {@code PRIORITY_LOW} has the lowest ranking.
     */
    public static final Map<String, Integer> RANKING = new HashMap<>();

    public final String priority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid {@code Priority}.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = priority;

        // Initialize the RANKING mapping
        RANKING.put(PRIORITY_LOW, 3);
        RANKING.put(PRIORITY_MEDIUM, 2);
        RANKING.put(PRIORITY_HIGH, 1);
    }

    /**
     * Returns true if a given string is a valid {@code Priority}.
     */
    public static boolean isValidPriority(String test) {
        return test.equals(PRIORITY_LOW) || (test.equals(PRIORITY_MEDIUM) || test.equals(PRIORITY_HIGH));
    }


    @Override
    public String toString() {
        return priority;
    }

    /**
     * Compares this {@code Priority} to another {@code Priority}.
     *
     * @param other The other {@code Priority} object
     * @return      -1 if this object is lesser, 0 if they are equal, 1 otherwise
     */
    public int compareTo(Priority other) {
        Integer thisRanking = RANKING.get(this.priority);
        Integer otherRanking = RANKING.get(other.priority);
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
