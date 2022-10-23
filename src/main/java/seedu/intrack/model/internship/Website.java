package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's website in the internship tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidWebsite(String)}
 */
public class Website {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * These website name could have these following components:
     * - ftp or http or https (optional)
     * - www (optional)
     * - url name
     * - valid subdomain
     */
    public static final String VALIDATION_REGEX = "^((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http|https|www.))"
            + "[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\w+(&[a-zA-Z0-9_]+=\\w+)*)?\\/?$";

    public final String value;

    /**
     * Constructs an {@code Website}.
     *
     * @param website A valid website.
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
        return test.matches(VALIDATION_REGEX);
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
