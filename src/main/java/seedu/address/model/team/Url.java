package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Link's url in the address book.
 */
public class Url {

    public static final String MESSAGE_CONSTRAINTS = "The URL must start with either http or https and\n"
        + "then followed by :// and\n"
        + "then it must contain www. and\n"
        + "then followed by subdomain of length (2, 256) and\n"
        + "last part contains top level domain like .com, .org etc.";

    public static final String VALIDATION_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    public final String value;

    /**
     * Constructs an {@code Url}.
     *
     * @param url A valid url address.
     */
    public Url(String url) {
        requireNonNull(url);
        checkArgument(isValidUrl(url), MESSAGE_CONSTRAINTS);
        value = url;
    }

    /**
     * Returns if a given string is a valid url.
     */
    public static boolean isValidUrl(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Url // instanceof handles nulls
                && value.equals(((Url) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }




}
