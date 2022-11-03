package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's ID in the student map.
 * Guarantees: immutable
 */
public class StudentID {

    public final String value;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param studentID A valid name.
     */
    public StudentID(String studentID) {
        requireNonNull(studentID);
        this.value = studentID;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Returns string used for sorting studentID
     */
    public String toCmpString() {
        return this.value.isEmpty()
               ? null
               : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentID // instanceof handles nulls
                && value.equals(((StudentID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
