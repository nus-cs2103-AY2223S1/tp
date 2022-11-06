package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

/**
 * Represents a Person's uid that uniquely identifies the person in the contact list of the application.
 * Uid is an unmodifiable field that is created every time with the creation of a new Person.
 * Guarantees: immutable; is valid as declared in {@link #isValidUid(String)}
 */
public class Uid {
    public static final String MESSAGE_CONSTRAINTS = "Uid should be a string parsed from java UUID object";
    public final String value;

    /**
     * Constructor that creates an Uid Object.
     */
    public Uid() {
        this.value = String.valueOf(UUID.randomUUID());
    }
    /**
     * Overloaded constructor that creates an Uid Object.
     */
    public Uid(String value) {
        requireNonNull(value);
        this.value = value;
    }
    public String getUid() {
        return value;
    }
    /**
     * Returns true if a given string is a valid Uid.
     */
    public static boolean isValidUid(String uidToTest) {
        requireNonNull(uidToTest);
        try {
            UUID uuid = UUID.fromString(uidToTest);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Uid // instanceof handles nulls
                && value.equals(((Uid) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
