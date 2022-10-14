package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Homework {
    public final String value;

    /**
     * Constructs an {@code Homework}.
     *
     * @param homework A description of the homework.
     */
    public Homework(String homework) {
        requireNonNull(homework);
        value = homework;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
                && value.equalsIgnoreCase(((Homework) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
