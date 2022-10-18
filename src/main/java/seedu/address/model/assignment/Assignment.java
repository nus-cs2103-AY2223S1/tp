package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignment(String)}
 */
public class Assignment implements Comparable<Assignment> {

    public static final String MESSAGE_CONSTRAINTS = "Assignment can take any values, and it should not be blank";

    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String value;

    private Workload workload;

    /**
     * Empty constructor to prevent error reading from jsonFile.
     */
    public Assignment() {};

    /**
     * Constructs an {@code Assignment}.
     *
     * @param name A valid name.
     */
    public Assignment(String name) {
        requireNonNull(name);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        this.value = name;
    }

    /**
     * Constructs an assignment with workload and name
     * @param name A valid name.
     * @param workload A valid workload.
     */
    public Assignment(String name, Workload workload) {
        requireNonNull(name);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        this.value = name;
        this.workload = workload;
    }

    /**
     * Returns true if a given string is a valid assignment.
     */
    public static boolean isValidAssignment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Get name of assignment
     */
    public String getAssignment() {
        return value;
    }

    /**
     * Get workload of assignment
     */
    public Workload getWorkload() {
        return workload;
    }

    @Override
    public String toString() {
        //If workload is specified
        if (getWorkload() != null) {
            return value + " (" + workload + ")";
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && value.equals(((Assignment) other).value));// state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    //Compare deadline and workload with other assignments
    @Override
    public int compareTo(Assignment otherAssignment) {
        if (getWorkload() == null && otherAssignment.getWorkload() == null) {
            return 0;
        } else if (getWorkload() == null) {
            return 1;
        } else if (otherAssignment.getWorkload() == null) {
            return -1;
        } else {
            return Integer.compare(getWorkload().ordinal(),
                    otherAssignment.getWorkload().ordinal());
        }
    }
}
