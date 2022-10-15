package seedu.address.model.person;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's visitation status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVisitStatus(String)}
 */
public class VisitStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Visited status should either be 'true' or 'false'";

    /**
     * The first character of the visitStatus must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * It should be either "true" or "false", and is case-insensitive.
     */
    private static final String VISITED_REGEX = "^(?i)true$";
    private static final String NOT_VISITED_REGEX = "^(?i)false$";
    public static final String VALIDATION_REGEX = VISITED_REGEX + "|" + NOT_VISITED_REGEX;

    private static final String VISITED_MESSAGE = "visited";
    private static final String NOT_VISITED_MESSAGE = "not visited";

    private static final String VALID_VISITED_INPUT = "True";
    private static final String VALID_NOT_VISITED_INPUT = "False";

    private final String visitStatusString;
    private final boolean visitStatus;

    public VisitStatus(String visitStatusString) {
        requireNonNull(visitStatusString);
        checkArgument(isValidVisitStatus(visitStatusString), MESSAGE_CONSTRAINTS);
        this.visitStatusString = visitStatusString;
        this.visitStatus = parseVisitStatus(visitStatusString);
    }

    public boolean getVisitStatus() {
        return this.visitStatus;
    }

    public String getVisitStatusString() {
        return this.visitStatusString;
    }

    public static boolean isValidVisitStatus(String testVisitStatusString) {
        return testVisitStatusString.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if {@code visitStatusString} is a valid String representing a visit VisitedStatus.
     * Return false if it is a valid String represented an unvisited VisitStatus
     */
    public static boolean parseVisitStatus(String visitStatusString) {
        checkArgument(isValidVisitStatus(visitStatusString), MESSAGE_CONSTRAINTS);
        return Pattern.matches(VISITED_REGEX, visitStatusString);
    }

    /**
     * Returns a VisitStatus that is visited.
     */
    public static VisitStatus getVisited() {
        return new VisitStatus(VALID_VISITED_INPUT);
    }

    /**
     * Returns a VisitStatus that is not visited.
     */
    public static VisitStatus getNotVisited() {
        return new VisitStatus(VALID_NOT_VISITED_INPUT);
    }

    @Override
    public String toString() {
        return this.visitStatus ? VISITED_MESSAGE : NOT_VISITED_MESSAGE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VisitStatus // instanceof handles nulls
                && visitStatusString.equals(((VisitStatus) other).visitStatusString)); // state check
    }

    @Override
    public int hashCode() {
        return visitStatusString.hashCode();
    }
}
