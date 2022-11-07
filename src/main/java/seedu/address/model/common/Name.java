package seedu.address.model.common;

/**
 * Abstract class for all Name objects.
 */
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
    public abstract boolean equals(Object other);

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
