package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's response count in the SETA application.
 */
public class Response {
    public static final String MESSAGE_CONSTRAINTS =
            "Response should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String value;

    /**
     * Constructs a {@code Response}.
     *
     * @param response A valid response number.
     */
    public Response(String response) {
        requireNonNull(response);
        checkArgument(isValidResponse(response), MESSAGE_CONSTRAINTS);
        value = response;
    }

    /**
     * Returns true if a given string is a valid response number.
     */
    public static boolean isValidResponse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Response // instanceof handles nulls
                && value.equals(((Response) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
