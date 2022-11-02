package coydir.model.person;

import static coydir.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Person's employee ID in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmployeeId(String)}
 */
public class EmployeeId {

    public static final String MESSAGE_CONSTRAINTS =
            "Employee ID is entirely managed by Coydir. It is a fixed number provided by Coydir. Please do not tamper.";

    /*
     * The first character of the position must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+";

    private static int count = 1;

    private static Set<Integer> allIds = new HashSet<Integer>();

    public final String value;

    /**
     * Constructs a {@code EmployeeId}.
     */
    public EmployeeId() {
        allIds.add(count);
        this.value = String.format("%d", count++);
    }

    /**
     * Constructs a {@code EmployeeId} with a given ID.
     * Uses the raw integer value of the ID (no leading zeroes).
     * Called by factory method {@code addEmployeeId}.
     * Also used primarily in testing.
     */
    public EmployeeId(String id) {
        requireNonNull(id);
        checkArgument(isValidNumber(id), MESSAGE_CONSTRAINTS);
        this.value = String.valueOf(Integer.parseInt(id));
    }

    public static void restart() {
        allIds.clear();
        count = 1;
    }

    public static EmployeeId addEmployeeId(String id) {
        requireNonNull(id);
        checkArgument(isValidEmployeeId(id), MESSAGE_CONSTRAINTS);
        int rawValue = Integer.parseInt(id);
        allIds.add(rawValue);
        return new EmployeeId(String.valueOf(rawValue));
    }

    /**
     * Returns true if a given string is a valid number, and can possibly be an employee ID.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid new Employee ID.
     */
    public static boolean isValidEmployeeId(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        int testValue = Integer.parseInt(test);
        return testValue < count && !allIds.contains(testValue);
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
                && Integer.valueOf(this.value).equals(Integer.valueOf(((EmployeeId) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

}
