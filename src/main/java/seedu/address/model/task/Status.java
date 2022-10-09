package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the task list.
 */
public class Status {
    private static final String completedSymbol = "\u2713";
    private static final String incompleteSymbol = "\u2716";

    public static final String MESSAGE_CONSTRAINTS = "Status should only be either U+2713 or U+2716.";

    public final String status;

    public Status(boolean isComplete) {
        requireNonNull(isComplete);
        this.status = isComplete ? completedSymbol : incompleteSymbol;
    }

    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Returns true if a given string represents a status.
     */
    public static boolean isValidStatus(String test) {
        return test.equals(completedSymbol) || test.equals(incompleteSymbol);
    }

    public boolean getIsComplete() {
        return status.equals(completedSymbol);
    }

    @Override
    public String toString() {
        return status;
    }
}
