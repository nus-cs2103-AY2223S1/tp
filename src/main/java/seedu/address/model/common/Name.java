package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

public abstract class Name {

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        fullName = name;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    abstract public boolean equals(Object other);

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
