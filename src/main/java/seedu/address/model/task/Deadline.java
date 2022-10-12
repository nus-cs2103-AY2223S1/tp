package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the deadline of a task in the TaskList.
 */
public class Deadline {
    // TODO: implement this class
    // TODO: edit MESSAGE_CONSTRAINTS
    public static final String MESSAGE_CONSTRAINTS = " ";


    // TODO: Edit VALIDATION_REGEX (Copied from Description class for now)
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    // TODO: Implement deadline (e.g. DateTime... )
    private final String deadline;

    // TODO: Implement constructor
    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);

        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = deadline;
    }

    // TODO: Implement isValidDeadline method
    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDeadline(String test) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }
}
