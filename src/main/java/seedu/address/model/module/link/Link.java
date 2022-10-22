package seedu.address.model.module.link;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Link in the address book.
 * Guarantees: immutable;
 * link alias is valid as declared in {@link #isValidLinkAlias(String)}
 * link URL is valid as declared in {@link #isValidLinkUrl(String)}
 */
public class Link implements Comparable<Link> {

    public static final String MESSAGE_CONSTRAINTS_ALIAS =
            "link aliases should contain only alphanumeric or whitespace characters (1-15 characters long)";
    public static final String MESSAGE_CONSTRAINTS_URL =
            "link URLs should have no whitespaces and should have one top level domain (e.g. <.com>)";

    //obtained from https://uibakery.io/regex-library/url-regex-java
    //Currently allows links with multiple dots (some browsers have the functionality to ignore multiple dots)
    //http (non secured) is also allowed
    //Todo: improve on link validation & state clearly exactly what type of link allowed
    public static final String VALIDATION_REGEX_URL_WITH_HTTPS = "^https?:\\/\\/(?:www\\.)?"
            + "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\."
            + "[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
    public static final String VALIDATION_REGEX_URL_WITHOUT_HTTPS =
            "^[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}"
            + "\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";
    public static final String VALIDATION_REGEX_ALIAS = "^[a-zA-Z0-9 ]{1,15}$";
    public final String linkAlias;
    public final String linkUrl;

    /**
     * Constructs a {@code Link}.
     * @param linkAlias A link alias.
     * @param linkUrl A link URL.
     */
    public Link(String linkAlias, String linkUrl) {
        requireNonNull(linkAlias);
        requireNonNull(linkUrl);
        checkArgument(isValidLinkAlias(linkAlias), MESSAGE_CONSTRAINTS_ALIAS);
        checkArgument(isValidLinkUrl(linkUrl), MESSAGE_CONSTRAINTS_URL);
        this.linkAlias = linkAlias;
        this.linkUrl = linkUrl;
    }

    /**
     * Returns true if a given string is a valid link URL.
     */
    public static boolean isValidLinkUrl(String test) {
        return test.trim().matches(VALIDATION_REGEX_URL_WITHOUT_HTTPS)
                || test.matches(VALIDATION_REGEX_URL_WITH_HTTPS);
    }

    /**
     * Returns true if a given string is a valid link alias.
     */
    public static boolean isValidLinkAlias(String test) {
        return test.trim().matches(VALIDATION_REGEX_ALIAS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && linkAlias.equals(((Link) other).linkAlias) // state check
                && linkUrl.equals(((Link) other).linkUrl)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkAlias.hashCode(), linkUrl.hashCode());
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + linkAlias + ";" + linkUrl + ']';
    }

    //Java TreeSet uses compareTo for equality
    @Override
    public int compareTo(Link other) {
        return this.linkAlias.compareTo(other.linkAlias);
    }
}

