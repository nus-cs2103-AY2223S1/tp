package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's venue in ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenue(String)}
 */
public class ConsultationVenue {
    public static final String MESSAGE_CONSTRAINTS =
            "Venue should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the venue must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String venue;

    /**
     * Constructs a {@code ConsultationVenue}.
     *
     * @param venue A valid venue.
     */
    public ConsultationVenue(String venue) {
        requireNonNull(venue);
        checkArgument(isValidVenue(venue), MESSAGE_CONSTRAINTS);
        this.venue = venue;
    }

    /**
     * Returns true if a given string is a valid venue.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return venue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationVenue // instanceof handles nulls
                && venue.equals(((ConsultationVenue) other).venue)); // state check
    }

    @Override
    public int hashCode() {
        return venue.hashCode();
    }
}
