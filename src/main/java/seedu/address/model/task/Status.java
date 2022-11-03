package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the task list.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "String should only be either \"Complete\" or \"Not complete\".";

    private static final String completedSymbol = "\u2713";
    private static final String incompleteSymbol = "\u2716";
    private static final String completedString = "Complete";
    private static final String incompleteString = "Not complete";

    private final boolean isComplete;

    /**
     * Constructs a {@code Status} with a boolean parameter. Every field must be present and not null.
     *
     * @param isComplete A boolean indicating whether a Status is complete.
     */
    public Status(boolean isComplete) {
        requireNonNull(isComplete);
        this.isComplete = isComplete;
    }

    /**
     * Constructs a {@code Status} with a String parameter. Every field must be present and not null.
     *
     * @param status A valid string representing a status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.isComplete = status.equals(completedString);
    }

    /**
     * Returns true if a given string represents a status.
     */
    public static boolean isValidStatus(String test) {
        return test.equals(completedString) || test.equals(incompleteString);
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public String toDisplayString() {
        return isComplete ? completedSymbol : incompleteSymbol;
    }

    @Override
    public String toString() {
        return isComplete ? completedString : incompleteString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof Status) { // instanceof handles nulls
            Boolean thisBoolean = this.isComplete;
            Boolean otherBoolean = ((Status) other).getIsComplete();
            return thisBoolean.equals(otherBoolean); // state check
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(this.isComplete);
    }

}
