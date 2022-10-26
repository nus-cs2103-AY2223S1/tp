package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.AppUtil.checkArgument;

import java.net.URISyntaxException;
import java.net.URI;

/**
 * Represents an Internship's website in the internship tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidWebsite(String)}
 */
public class Website {

    public static final String MESSAGE_CONSTRAINTS = "Please enter a valid URL";

    public final String value;

    /**
     * Constructs an {@code Website}.
     *
     * @param website A valid website.
     */
    public Website(String website) {
        requireNonNull(website);
        checkArgument(isValidWebsite(website), MESSAGE_CONSTRAINTS);

        value = addHttp(website);
    }

    /**
     * Returns true if a given string is a valid website.
     */
    public static boolean isValidWebsite(String test) {
        try {
            URI uri = new URI(test);
            if (test.contains(" ") || test.isBlank()) {
                return false;
            }
            return true;
        } catch (URISyntaxException mue) {
            return false;
        }
    }

    /**
     * Adds http to the website if valid URI but does not contain protocol
     */
    public static String addHttp(String uri) {
        if (!uri.matches("^\\w+?://.*")) {
            uri = "http://" + uri;
        }
        return uri;
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
