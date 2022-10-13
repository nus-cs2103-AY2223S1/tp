package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's unique id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class PersonId {
    public static final String MESSAGE_CONSTRAINTS =
            "Person Id must be a non-negative number, and it should not be blank";

    public final Integer id;

    /**
     * Constructs a {@code PersonId}.
     * Takes a string id and parses it into an Integer.
     *
     * @param id A valid id.
     */
    public PersonId(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = Integer.parseInt(id);
    }

    /**
     * Constructs a {@code InternshipId}.
     *
     * @param id A valid id.
     */
    public PersonId(Integer id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if the given String can be parsed into an Integer and is non-negative.
     */
    public static boolean isValidId(String test) {
        try {
            return isValidId(Integer.parseInt(test));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidId(Integer test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonId // instanceof handles nulls
                && id.equals(((PersonId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
