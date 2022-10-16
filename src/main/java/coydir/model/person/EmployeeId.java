package coydir.model.person;

import static coydir.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's employee ID in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmployeeId(String)}
 */
public class EmployeeId {

    public static final String MESSAGE_CONSTRAINTS =
            "Employee ID is entirely managed by Coydir. It is never blank, and is a fixed number provided by Coydir.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+";

    private static int count = 1;

    public final String value;

    /**
     * Constructs a {@code EmployeeId}.
     */
    public EmployeeId() {
        this.value = String.format("%d", count++);
    }

    /**
     * Constructs a {@code EmployeeId} with a given ID.
     */
    public EmployeeId(String id) {
        requireNonNull(id);
        checkArgument(isValidEmployeeId(id), MESSAGE_CONSTRAINTS);
        this.value = id;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEmployeeId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Sets the current employee count and thus next employee ID.
     */
    public static void setCount(int count) {
        EmployeeId.count = count;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeeId // instanceof handles nulls
                && this.value.equals(((EmployeeId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
