package seedu.address.model.module.link;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
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
            "Link URLs should have one top level domain (e.g. <.com>, <.org>) and no whitespaces.\n"
                    + "Character constraints for and on the left of the top level domain:\n"
                    + "Only hyphen or alphanumeric characters allowed. "
                    + "(Except for the specific use of \"https://\" or \"http://\" at the front)\n"
                    + "Character constraints on the right of the top level domain:\n"
                    + "Any non-whitespace characters found on a typical english keyboard.";
    public static final String LINK_HEADER_PROTOCOL_HTTPS = "https://";
    public static final String LINK_HEADER_PROTOCOL_HTTP = "http://";

    //Check -> segment given string into 5 parts -> Scheme, Subdomain, Domain, Top-level Domain, Path.
    //Scheme(Optional): Must match either 'https://' or 'http://'
    //Subdomain, Domain, Top-level Domain: Only hyphen and alphanumeric characters allowed
    //Domain (Subdomain, Domain, Top-level Domain) rules obtained from: www.rfc-editor.org/rfc/rfc3986
    //Does not support international domains: e.g. chinese/greek/russian characters
    //Path: All printable ASCII characters allowed except for whitespace.
    //Solution below adapted from https://uibakery.io/regex-library/url-regex-java
    public static final String VALIDATION_REGEX_URL = "^(?:https?:\\/\\/)?" //https scheme [optional]
                    + "(?:[-a-zA-Z0-9]*\\.)?" //subdomain (e.g. 'www', 'open', 'video', 'blog') [optional]
                    + "[-a-zA-Z0-9]{1,256}\\." //domain (e.g. 'google', 'youtube', 'wikipedia')
                    + "[-a-zA-Z0-9]*\\b" // top level domain (e.g. 'com', 'org', 'net')
                    + "([\\x21-\\x7E]*)$"; //path (to a specific part of the link) [optional]
    public static final String VALIDATION_REGEX_ALIAS = "^[a-zA-Z0-9 ]{1,15}$";
    private static final String OS_NAME_LOWERCASE_WINDOWS = "windows";
    private static final String WINDOWS_OPEN_LINK_COMMAND_KEY = "rundll32 url.dll,FileProtocolHandler ";
    private static final String OS_NAME_LOWERCASE_MAC = "mac";
    private static final String MAC_OPEN_LINK_COMMAND_KEY = "open ";
    private static final String OS_NAME_LOWERCASE_LINUX = "linux";
    private static final String LINUX_OPEN_LINK_COMMAND_KEY = "xdg-open";
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
        return testTrimmed.matches(VALIDATION_REGEX_URL);
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

    //@@author shwene-reused
    //Reused from https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
    //with slight modification

    /**
     * Opens the link URL associated with the Link object from which this method is called from.
     * The link URL will be opened in the user's default browser through Java's Runtime Class.
     * @throws IOException if the user does not have a browser installed or when the user disables direct browser
     *     access from its operating system command terminal (enabled by default).
     * @throws SecurityException if the user prevents access to its operating system information or when
     *     the user prevents Java from interacting with its operating system command terminal (allowed by default).
     */
    public void open() throws IOException, SecurityException {
        assert linkUrl != null;
        String finalLinkUrl = linkUrl;
        boolean isLinkUrlPaddedWithHttps = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTPS);
        boolean isLinkUrlPaddedWithHttp = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTP);
        if (!(isLinkUrlPaddedWithHttps || isLinkUrlPaddedWithHttp)) {
            finalLinkUrl = LINK_HEADER_PROTOCOL_HTTP + linkUrl;
        }
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        if (os.contains(OS_NAME_LOWERCASE_WINDOWS)) {
            rt.exec(WINDOWS_OPEN_LINK_COMMAND_KEY + finalLinkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_MAC)) {
            rt.exec(MAC_OPEN_LINK_COMMAND_KEY + finalLinkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_LINUX)) {
            String[] cmd = {LINUX_OPEN_LINK_COMMAND_KEY, finalLinkUrl};
            rt.exec(cmd);
        }
    }
    //@@author

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

