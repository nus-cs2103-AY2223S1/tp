package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's class in the address book.
 * Guarantees: immutable; is always valid
 */
public class ClassGroup {
    public final String value;

    /**
     * Constructs an {@code ClassGroup}.
     *
     * @param classGroup A valid class group.
     */
    public ClassGroup(String classGroup) {
        requireNonNull(classGroup);
        this.value = classGroup;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassGroup // instanceof handles nulls
                && value.equals(((ClassGroup) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
