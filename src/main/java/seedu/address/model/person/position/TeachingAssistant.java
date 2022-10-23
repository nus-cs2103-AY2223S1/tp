package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;

/**
 * Represents the Teaching Assistant position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class TeachingAssistant extends Position {

    /**
     * Availability of the teaching assistant.
     */
    public enum Availability {
        AVAILABLE,
        UNAVAILABLE
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Availability can only be available or unavailable (non case-sensitive).";

    private String availability;

    /**
     * Creates a teaching assistant and initialises their availability to available.
     */
    public TeachingAssistant() {
        super("TA");
        availability = "Available";
    }

    /**
     * Creates a teaching assistant with the given details.
     * @param availability of the teaching assistant
     */
    public TeachingAssistant(String availability) {
        super("TA");
        this.availability = availability;
    }

    @Override
    public void setDetails(String availability) {
        requireNonNull(availability);
        this.availability = availability;
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
    public String toShow() {
        return "Availability: " + availability;
    }

    @Override
    public String toString() {
        return "Teaching Assistant: " + availability;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 0;
    }

    @Override
    public String getDetails() {
        return availability;
    }
}
