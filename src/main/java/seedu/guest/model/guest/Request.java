package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

/**
 * Represents the Request of a guest.
 * Guarantees:
 * Case 1: details are present and not null, field values are validated, immutable.
 * Case 2: there are no specific Request.
 */
public class Request {

    // length constraints
    private static final int MAX_LENGTH_REQUEST = 500;

    public static final String MESSAGE_CONSTRAINTS = "Requests must be at most " + MAX_LENGTH_REQUEST
            + " characters long.";
    public final String value;

    /**
     * Constructs a {@code Request}.
     */
    public Request() {
        value = "-";
    }

    /**
     * Constructs a {@code Request}.
     *
     * @param request the specific Request
     */
    public Request(String request) {
        requireNonNull(request);
        checkArgument(isValidRequest(request), MESSAGE_CONSTRAINTS);
        value = request.trim();
    }

    /**
     * Returns true if a given string is a valid request.
     */
    public static boolean isValidRequest(String inputRequest) {
        return (inputRequest.trim().length() <= MAX_LENGTH_REQUEST);
    }

    /**
     * return the String representation of Request.
     * @return String
     */
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Request // instanceof handles nulls
                && value.equals(((Request) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
