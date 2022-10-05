package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Response {

    public static final String MESSAGE_CONSTRAINTS =
            "Response should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String response;

    /**
     * Constructs a {@code Response}.
     *
     * @param response A valid response number.
     */
    public Response(String response) {
        requireNonNull(response);
        checkArgument(isValidResponse(response), MESSAGE_CONSTRAINTS);
        this.response = response;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidResponse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return response;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Response // instanceof handles nulls
                && response.equals(((Response) other).response)); // state check
    }

    @Override
    public int hashCode() {
        return response.hashCode();
    }
}
