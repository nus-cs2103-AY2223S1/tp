package seedu.intrack.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's remark in the tracker.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;
    /**
     * Constructs a {@code Remark}.
     *
     * @param remark Remark or note that a user added to application.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Remark
                && value.equals(((Remark) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
