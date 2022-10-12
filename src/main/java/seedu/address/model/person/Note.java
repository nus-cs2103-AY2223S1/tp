package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents an optional note about a person in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes should only contain alphanumeric characters and spaces, "
            + "and it should not be blank. If you do not want to add a note, please leave the field blank. "
            + "Blank notes will be ignored.";

    public final String value;

    /**
     * Constructs a {@code note}.
     *
     * @param s A valid note.
     */
    public Note(String s) {
        requireNonNull(s);
        value = s.trim();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                        && value.equals(((Note) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
