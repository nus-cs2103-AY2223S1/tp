package friday.model.student;

import static java.util.Objects.requireNonNull;

// @@author HowSuen-reused
// Reused from https://nus-cs2103-ay2223s1.github.io/tp/tutorials/AddRemark.html

/**
 * Represents a Student's remark in FRIDAY.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A string remark
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if the given remark is empty.
     */
    public boolean isEmpty() {
        return value == "";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
