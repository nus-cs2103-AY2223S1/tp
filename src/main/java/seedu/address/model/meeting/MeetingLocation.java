package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Meeting Date in the FinBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMeetingLocation(String)}
 */
public class MeetingLocation {

    public static final String MESSAGE_CONSTRAINTS = "Locations can take any values.";

    public static final String PLACEHOLDER_VALUE = "TBC";

    /*
     * The first character of the risk must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final Optional<String> value;

    private final Optional<Boolean> isVirtual;

    /**
     * Constructs a {@code Date}. Meeting location can be null
     *
     * @param location A valid location.
     */
    public MeetingLocation(String location) {
        if (location != null && !location.isEmpty() && !location.equals(PLACEHOLDER_VALUE)) {
            checkArgument(isValidMeetingLocation(location), MESSAGE_CONSTRAINTS);
            isVirtual = Optional.of(checkIsVirtual(location));
            value = Optional.of(location);
        } else {
            isVirtual = Optional.empty();
            value = Optional.empty();
        }
    }

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidMeetingLocation(String test) {
        return true;
    }

    /**
     * Returns true if a given location is virtual or in-person.
     * A location is virtual if it is a valid URL.
     */
    public static boolean checkIsVirtual(String test) {
        try {
            new URL(test);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MeetingLocation // instanceof handles nulls
            && (value.equals(((MeetingLocation) other).value)
            && isVirtual.equals(((MeetingLocation) other).isVirtual))); // state check
    }

    /**
     * Returns the value of the meeting location.
     */
    public String get() {
        return value.orElse(PLACEHOLDER_VALUE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isVirtual);
    }

    /**
     * Returns whether a meeting is virtual or not as a String.
     */
    public String getVirtualStatus() {
        return isVirtual.map(isVirtual -> isVirtual ? "Online" : "In-person").orElse("TBC");
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value.orElse(PLACEHOLDER_VALUE);
    }

}

