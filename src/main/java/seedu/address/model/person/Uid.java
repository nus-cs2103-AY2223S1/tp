package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

/**
 * Uid that uniquely identifies a person.
 * Uid is an unmodifiable field that is created every time with the creation of a new Person.
 */
public class Uid {
    public static final String MESSAGE_CONSTRAINTS = "Uid should be a string parsed from java UUID object";
    public final String value;

    /**
     * Constructor that creates a Uid Object.
     */
    public Uid() {
        this.value = String.valueOf(UUID.randomUUID());
    }
    /**
     * Overloaded constructor that creates a Uid Object.
     */
    public Uid(String value) {
        requireNonNull(value);
        this.value = value;
    }
    public String getUid() {
        return value;
    }
    /**
     * Returns true if both uids have the same value. This method should always return false value.
     */
    public boolean isSameUid(Uid otherUid) {
        if (otherUid == this) {
            return true;
        }
        return otherUid != null && otherUid.getUid().equals(getUid());
    }

    /**
     * Returns true if a given string is a valid Uid.
     */
    public static boolean isValidUid(String test) {
        try {
            UUID uuid = UUID.fromString(test);
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
}
