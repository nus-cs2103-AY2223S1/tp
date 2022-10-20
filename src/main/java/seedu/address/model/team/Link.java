package seedu.address.model.team;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents a link that is tracked in each team.
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS =
            "Link names should not be blank and cannot begin with a whitespace";

    public static final String URL_CONSTRAINTS =
            "Link URLs must be a valid url ";

    /**
     * Name of the link
     */
    private Name displayedName;

    private Url url;

    /**
     * Constructs a {@code Link}
     * @param displayedName A valid name to display.
     * @param url A valid URL object.
     */
    public Link(Name displayedName, Url url) {
        requireNonNull(displayedName);
        requireNonNull(url);
        this.displayedName = displayedName;
        this.url = url;
    }

    public Name getDisplayedName() {
        return this.displayedName;
    }

    public Url getUrl() {
        return this.url;
    }

    /**
     * Returns true if both links have the same name.
     * This defines a weaker notion of equality between two links.
     */
    public boolean isSameLink(Link otherLink) {
        if (otherLink == this) {
            return true;
        }

        return otherLink != null
                && otherLink.getDisplayedName().equals(getDisplayedName());
    }

    /**
     * Returns true if the two links have the same name, and the same URL.
     * @param other the other link to be compared with.
     * @return true if the links are considered equal, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Link // instanceof handles nulls
                && displayedName.equals(((Link) other).displayedName))
                && url.equals(((Link) other).url);
    }

    @Override
    public int hashCode() {
        //use this method for custom field hashing instead of implementing on your own
        return Objects.hash(displayedName, url);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDisplayedName())
                .append("; URL: ")
                .append(getUrl());

        return builder.toString();
    }

}
