package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Website {
    public final String value;
    public static final String DEFAULT = "NA";

    // TODO update with the constraints
    public static final String MESSAGE_CONSTRAINTS = "Web should be of the format of [TODO] ";

    // TODO update validation regex
    // TODO must account for NA
    //@@author TomC-reused
    //Reused from https://stackoverflow.com/questions/163360
    public static final String VALIDATION_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    //@@author

    /**
     * Constructs an {@code Website}.
     *
     * @param website A valid website address.
     */
    public Website(String website) {
        requireNonNull(website);
        checkArgument(isValidWebsite(website), MESSAGE_CONSTRAINTS);
        value = website;
    }

    public static boolean isValidWebsite(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("NA");
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
