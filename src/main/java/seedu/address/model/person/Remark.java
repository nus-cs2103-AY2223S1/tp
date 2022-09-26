package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Encapsulates a remark field for a person.
 */
public class Remark {
    public final String value;

    /**
     * Constructor for a Remark instance.
     * @param value The value of the remark.
     */
    public Remark(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Remark) {
            Remark r = (Remark) o;
            return this.value.equals(r.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
