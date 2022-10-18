package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Email;

/**
 * Represents a Link's url in the address book.
 */
public class Url {

    public static final String MESSAGE_CONSTRAINTS = "Links should be of the following format\n"
            + "[Optional] Protocol 'https://'\n"
            + "[Optional] Sub domain 'www.'\n"
            + "[Required] Root domain 'google'\n"
            + "[Required] Top level domain '.com'\n"
            + "[Optional] Subdirectories '/home'\n"
            + "[Optional] Query string '?yes=1'";

    public static final String VALIDATION_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)([).!';/?:,][[:blank:]])?$";

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
