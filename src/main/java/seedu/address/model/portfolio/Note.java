package seedu.address.model.portfolio;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the clients' important notes in the FinBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidNote(String)}}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes should be alphanumeric";

    public final String value;

    /**
     * Constructs a {@code Note}. Notes can be null
     *
     * @param note A valid note.
     */
    public Note(String note) {
        if (note != null && !note.isEmpty()) {
            checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        }
        this.value = note;

    }

    /**
     * Returns true if a given string is a valid note.
     * Always true because notes string should not be constrained.
     */
    public static boolean isValidNote(String test) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && (value != null && value.equals(((Note) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }
}
