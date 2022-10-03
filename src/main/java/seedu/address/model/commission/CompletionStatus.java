package seedu.address.model.commission;

import java.util.Objects;

/**
 * Represents a Commission's completion status in the address book.
 * Guarantees: immutable.
 */
public class CompletionStatus {
    public final boolean isCompleted;

    /**
     * Constructs a {@code Completion}
     *
     * @param isCompleted Completion status of commission.
     */
    public CompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.valueOf(isCompleted);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CompletionStatus
                && isCompleted == ((CompletionStatus) other).isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isCompleted);
    }
}
