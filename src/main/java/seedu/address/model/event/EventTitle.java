package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Event's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventTitle(String)}
 */
public class EventTitle implements Comparable<EventTitle> {

    public static final String MESSAGE_CONSTRAINTS =
            "Event Title should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String eventTitle;

    /**
     * Constructs a {@code EventTitle}.
     *
     * @param eventTitle A valid title for the event.
     */
    public EventTitle(String eventTitle) {
        requireNonNull(eventTitle);
        checkArgument(isValidEventTitle(eventTitle), MESSAGE_CONSTRAINTS);
        this.eventTitle = eventTitle;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEventTitle(String eventTitleToTest) {
        return eventTitleToTest.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(EventTitle e) {
        return this.eventTitle.compareToIgnoreCase(e.eventTitle);
    }

    @Override
    public String toString() {
        return eventTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTitle // instanceof handles nulls
                && eventTitle.equals(((EventTitle) other).eventTitle)); // state check
    }

    @Override
    public int hashCode() {
        return eventTitle.hashCode();
    }

}
