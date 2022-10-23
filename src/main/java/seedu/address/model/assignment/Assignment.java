package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    private Deadline deadline;

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
        requireAllNonNull(name, workload);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        this.value = name;
        this.workload = workload;
    }

    /**
     * Constructs an assignment with name, workload and deadline
     * @param name A valid name.
     * @param workload A valid workload.
     * @param deadline A valid deadline.
     */
    public Assignment(String name, Workload workload, Deadline deadline) {
        requireAllNonNull(name, workload, deadline);
        checkArgument(isValidAssignment(name), MESSAGE_CONSTRAINTS);
        this.value = name;
        this.workload = workload;
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid assignment.
     */
    public static boolean isValidAssignment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getAssignment() {
        return value;
    }

    public Workload getWorkload() {
        return workload;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        if (getDeadline() != null && getWorkload() != null) {
            return value + " (" + workload + ") by: " + deadline.getDeadlineString() + "\n";
        } else if (getWorkload() != null) {
            return value + " (" + workload + ")\n";
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && value.equals(((Assignment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    //Compare workload with other assignments and sort based on workload.
    //High will appear on top of the list, followed by medium then low.
    @Override
    public int compareTo(Assignment otherAssignment) {
        return Integer.compare(otherAssignment.getWorkload().ordinal(),
                getWorkload().ordinal());
    }

}
