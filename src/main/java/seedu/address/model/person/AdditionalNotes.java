package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class AdditionalNotes {

    public final String value;

    /**
     * Constructs an {@code AdditionalNotes}.
     *
     * @param additionalNotes It can be empty or non-empty.
     */
    public AdditionalNotes(String additionalNotes) {
        requireNonNull(additionalNotes);
        value = additionalNotes;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdditionalNotes // instanceof handles nulls
                && value.equals(((AdditionalNotes) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
