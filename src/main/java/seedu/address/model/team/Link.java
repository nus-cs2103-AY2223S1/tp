package seedu.address.model.team;

import static java.util.Objects.requireNonNull;

import java.net.URL;

import seedu.address.model.person.Person;

/**
 * Represents a link that is tracked in each team.
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS =
            "Link names should not be blank and cannot begin with a whitespace";

    /**
     * Name of the link
     */
    private String displayedName;

    private URL url;

    /**
     * Constructs a {@code Link}
     * @param displayedName A valid name to display.
     * @param url A valid URL object.
     */
    public Link(String displayedName, URL url) {
        requireNonNull(displayedName);
        requireNonNull(url);
        this.displayedName = displayedName;
        this.url = url;
    }

    public String getDisplayedName() {
        return this.displayedName;
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

}
