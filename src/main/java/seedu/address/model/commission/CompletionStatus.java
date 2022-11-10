package seedu.address.model.commission;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a Commission's completion status in the address book.
 * Guarantees: immutable.
 */
public class CompletionStatus {
    public static final String MESSAGE_CONSTRAINTS = "The commission completion status provided is invalid. "
            + "Accepted inputs for completed: T, True, Yes, Y/ for incomplete: F, False, N, No. (Case Insensitive)";
    private static final Pattern TRUE_REGEX = Pattern.compile("\\AT|TRUE|YES|Y\\z", Pattern.CASE_INSENSITIVE);
    private static final Pattern FALSE_REGEX = Pattern.compile("\\AF|FALSE|NO|N\\z", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALIDATION_REGEX = Pattern.compile(TRUE_REGEX + "|" + FALSE_REGEX,
            Pattern.CASE_INSENSITIVE);

    public final boolean isCompleted;

    /**
     * Constructs a {@code CompletionStatus}
     *
     * @param isCompleted Completion status of commission.
     */
    public CompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Factory method to create a {@code CompletionStatus}
     * @param status String representation of completion status.
     */
    public static CompletionStatus of(String status) {
        if (TRUE_REGEX.matcher(status).matches()) {
            return new CompletionStatus(true);
        }
        return new CompletionStatus(false);
    }

    public static boolean isValidStatus(String status) {
        return VALIDATION_REGEX.matcher(status).matches();
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
