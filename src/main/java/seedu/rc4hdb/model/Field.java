package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;

/**
 * Represents a field with a string value.
 */
public abstract class Field {

    public final String value;

    protected Field(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
