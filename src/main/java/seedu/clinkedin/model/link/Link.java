package seedu.clinkedin.model.link;

import static java.util.Objects.requireNonNull;

import java.net.URL;

/**
 * Represents an optional link for a person in the clinkedin book.
 * Guarantees: immutable;
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Link should be valid links, "
            + "and it should not be blank. If you do not want to add a link, please leave the field blank. "
            + "Blank links will be ignored.";

    public final URL link;

    public final String platform;

    /**
     * Constructs a {@code link}.
     *
     * @param link A valid link.
     */
    public Link(URL link) {
        requireNonNull(link);
        this.link = link;
        this.platform = generatePlatform(link);
    }

    private String generatePlatform(URL url) {
        if (url.getHost().contains("instagram")) {
            return "instagram";
        }
        if (url.getHost().contains("discord")) {
            return "discord";
        }
        if (url.getHost().contains("facebook")) {
            return "facebook";
        }
        if (url.getHost().contains("github")) {
            return "github";
        }
        if (url.getHost().contains("telegram")) {
            return "telegram";
        }
        if (url.getHost().contains("linkedin")) {
            return "linkedin";
        }
        if (url.getHost().contains("snapchat")) {
            return "snapchat";
        }
        if (url.getHost().contains("twitter")) {
            return "twitter";
        }
        return "general";
    }

    @Override
    public String toString() {
        return link.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && link.toString().equals(((Link) other).link.toString())); // state check
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

}
