package seedu.address.model.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Link in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLinkName(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS =
            "links should have no whitespace and should have one top level domain (e.g. <.com>)";

    //obtained from https://uibakery.io/regex-library/url-regex-java
    //Currently allows links with multiple dots (some browsers have the functionality to ignore multiple dots)
    //Todo: improve on link validation & more precise in exactly what type of link allowed
    public static final String VALIDATION_REGEX_WITH_HTTPS = "^https?:\\/\\/(?:www\\.)?" +
            "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\."
            + "[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
    public static final String VALIDATION_REGEX_WITHOUT_HTTPS =
            "^[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}" +
            "\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";
    public final String linkName;

    /**
     * Constructs a {@code Link}.
     *
     * @param linkName A valid link name.
     */
    public Link(String linkName) {
        requireNonNull(linkName);
        checkArgument(isValidLinkName(linkName), MESSAGE_CONSTRAINTS);
        this.linkName = linkName;
    }

    /**
     * Returns true if a given string is a valid link name.
     */
    public static boolean isValidLinkName(String test) {
        return test.matches(VALIDATION_REGEX_WITHOUT_HTTPS) || test.matches(VALIDATION_REGEX_WITH_HTTPS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && linkName.equals(((Link) other).linkName)); // state check
    }

    @Override
    public int hashCode() {
        return linkName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + linkName + ']';
    }

}

