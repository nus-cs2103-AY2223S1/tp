package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Teaching Assistant position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class TeachingAssistant extends Position {

    enum Availability {
        AVAILABLE,
        UNAVAILABLE
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Availability can only be available or unavailable (non case-sensitive).";

    private String availability;

    public TeachingAssistant() {
        super("TA");
        availability = "unavailable";
    }

    public void setAvailability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidAvailability(availability), MESSAGE_CONSTRAINTS);
        this.availability = availability;
    }

    public String getAvailability() {
        return availability;
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
        for (Availability availability: Availability.values()) {
            if (availability.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Teaching Assistant";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachingAssistant // instanceof handles nulls
                && availability.equals(((TeachingAssistant) other).availability)); // state check
    }

    @Override
    public int hashcode() {
        return availability.hashCode();
    }
}
