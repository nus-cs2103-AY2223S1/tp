package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's title in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class TaskTitle {
    public static final String MESSAGE_CONSTRAINTS =
            "Task title should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String title;

    /**
     * Constructs a {@code TaskTitle}.
     *
     * @param title A valid task title.
     */
    public TaskTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTitle // instanceof handles nulls
                && title.equals(((TaskTitle) other).title)); // state check
    }

}
