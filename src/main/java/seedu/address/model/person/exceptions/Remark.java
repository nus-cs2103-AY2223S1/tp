package seedu.address.model.person.exceptions;

import static java.util.Objects.requireNonNull;

public class Remark {
    public final String description;

    public Remark(String description) {
        requireNonNull(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Remark) {
            Remark r = (Remark) o;
            return this.description.equals(r.description);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }
}
