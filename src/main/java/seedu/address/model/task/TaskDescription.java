package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class TaskDescription {
    public static final String MESSAGE_CONSTRAINTS =
            "Task description should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String description;

    /**
     * Constructs a {@code TaskDescription}.
     *
     * @param description A valid task description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDescription // instanceof handles nulls
                && description.equals(((TaskDescription) other).description)); // state check
    }

}
