package seedu.address.model.module.link;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Link in the address book.
 * Guarantees: immutable;
 * link alias is valid as declared in {@link #isValidLinkAlias(String)}
 * link URL is valid as declared in {@link #isValidLinkUrl(String)}
 */
public class Link implements Comparable<Link> {

    public static final String MESSAGE_CONSTRAINTS_ALIAS =
            "Link aliases should contain only alphanumeric or whitespace characters, "
                    + "and be 1-15 characters long with at least one alphanumeric character.";
    public static final String MESSAGE_CONSTRAINTS_URL =
            "Link URLs should have no whitespaces and should have one top level domain (e.g. <.com>)";
    public static final String LINK_HEADER_PROTOCOL_HTTPS = "https://";
    public static final String LINK_HEADER_PROTOCOL_HTTP = "http://";

    //Currently allows links with multiple dots (some browsers have the functionality to ignore multiple dots)
    //Main check -> Allowed symbols -> alphanumeric, "()@:%_\+.~#?&\/=";
    //Domain has to be between 1-256 characters, top-level domain between 1-6 characters
    //@@author shwene-reused
    //Reused from https://uibakery.io/regex-library/url-regex-java with minor modifications
    public static final String VALIDATION_REGEX_URL_WITH_HTTPS = "^https?:\\/\\/(?:www\\.)?"
            + "[-a-zA-Z0-9@:%_\\+~#=]{1,256}\\."
            + "[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";
    public static final String VALIDATION_REGEX_URL_WITHOUT_HTTPS =
            "^(?:www\\.)?[-a-zA-Z0-9@:%_\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}"
            + "\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";
    //@@shwene
    public static final String VALIDATION_REGEX_ALIAS = "^[a-zA-Z0-9 ]{1,15}$";
    public final String linkAlias;
    public final String linkUrl;

    /**
     * Constructs a {@code Link}.
     * @param linkAlias A link alias.
     * @param linkUrl A link URL.
     */
    public Link(String linkAlias, String linkUrl) {
        requireAllNonNull(linkAlias, linkUrl);
        checkArgument(isValidLinkAlias(linkAlias), MESSAGE_CONSTRAINTS_ALIAS);
        checkArgument(isValidLinkUrl(linkUrl), MESSAGE_CONSTRAINTS_URL);
        this.linkAlias = linkAlias.trim();
        this.linkUrl = linkUrl.trim();
    }

    /**
     * Returns true if a given string is a valid link URL.
     */
    public static boolean isValidLinkUrl(String test) {
        final String testTrimmed = test.trim();
        return testTrimmed.matches(VALIDATION_REGEX_URL_WITHOUT_HTTPS)
                || testTrimmed.matches(VALIDATION_REGEX_URL_WITH_HTTPS);
    }

    /**
     * Returns true if a given string is a valid link alias.
     */
    public static boolean isValidLinkAlias(String test) {
        return test.trim().matches(VALIDATION_REGEX_ALIAS);
    }

    /**
     * Returns true if this link object has the same link alias as the given String link alias
     * using the in-built String equals() method.
     */
    public boolean hasLinkAlias(String linkAliasPlainText) {
        return this.linkAlias.equals(linkAliasPlainText);
    }

    /**
     * Returns true if this link object has the same link alias as another link object
     * using the in-built String equals() method.
     */
    public boolean isSameLinkAlias(Link other) {
        return this.linkAlias.equals(other.linkAlias);
    }

    /**
     * Returns true if this link object has the same link URL as another link object
     * using the in-built String equals() method, ignoring the link protocol of both link URLs if present.
     */
    public boolean isSameLinkUrlIgnoreProtocol(Link other) {
        String thisLinkUrlWithoutProtocol = removeLinkUrlProtocol(this.linkUrl);
        String otherLinkUrlWithoutProtocol = removeLinkUrlProtocol(other.linkUrl);
        return thisLinkUrlWithoutProtocol.equals(otherLinkUrlWithoutProtocol);
    }

    /**
     * Returns a String link URL plain text stripped of its protocol header if present.
     */
    static String removeLinkUrlProtocol(String urlPlainText) {
        if (urlPlainText.startsWith(LINK_HEADER_PROTOCOL_HTTPS)) {
            return urlPlainText.substring(LINK_HEADER_PROTOCOL_HTTPS.length());
        }
        if (urlPlainText.startsWith(LINK_HEADER_PROTOCOL_HTTP)) {
            return urlPlainText.substring(LINK_HEADER_PROTOCOL_HTTP.length());
        }
        return urlPlainText;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && this.isSameLinkAlias((Link) other) // state check
                && this.isSameLinkUrlIgnoreProtocol(((Link) other))); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkAlias.hashCode(), linkUrl.hashCode());
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s]", linkAlias);
    }

    //Java TreeSet uses compareTo for equality
    @Override
    public int compareTo(Link other) {
        return this.linkAlias.compareTo(other.linkAlias);
    }
}

