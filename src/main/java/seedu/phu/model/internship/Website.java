package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.util.AppUtil.checkArgument;

/**
 * Represents the website of the company of the internship.
 */
public class Website {
    public static final String MESSAGE_CONSTRAINTS = "Web must be a URL";

    //@@author TomC-reused
    //Reused from https://stackoverflow.com/questions/163360
    public static final String VALIDATION_REGEX =
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    //@@author

    public static final String DEFAULT_VALUE = "NA";

    public final String value;

    /**
     * Constructs an {@code Website}.
     *
     * @param website A valid website internship.
     */
    public Website(String website) {
        requireNonNull(website);
        checkArgument(isValidWebsite(website), MESSAGE_CONSTRAINTS);
        value = website;
    }

    /**
     * Returns true if a given string is a valid website.
     */
    public static boolean isValidWebsite(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals(DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Website // instanceof handles nulls
                && value.equals(((Website) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
