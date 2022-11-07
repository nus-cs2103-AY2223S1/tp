package seedu.rc4hdb.model;

import static java.util.Objects.requireNonNull;

/**
 * Represents a field with a string value.
 */
public abstract class StringField {

    public final String value;

    protected StringField(String value) {
        requireNonNull(value);
        this.value = value;
    }

    /**
     * Returns true if this Field's value string contains {@code substring}. This method is case-insensitive.
     * @return true if the field value is a substring of this field's value.
     */
    public boolean containsIgnoreCase(String substring) {
        return this.value.toLowerCase().contains(substring.toLowerCase());
    }

    /**
     * Returns true if {@code other} and {@code this} comply to the sub or super class relation and their {@code value}
     * must be the same, ignoring case (upper or lower).
     * @return true if the condition above is satisfied.
     */
    public boolean equalsIgnoreCase(StringField other) {
        if (this == other) {
            return false;
        }
        return this.isSubOrSuperClass(other)
                && this.value.equalsIgnoreCase(other.value);
    }

    /**
     * Returns true if {@code other} is a subclass of {@code this} or if {@code this} is a subclass of {@code other}
     * during runtime.
     * @return true if the condition above is satisfied.
     */
    private boolean isSubOrSuperClass(StringField other) {
        if (other == null) {
            return false;
        }
        return other.getClass().equals(this.getClass());
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StringField)) {
            return false;
        }

        StringField otherField = (StringField) other;
        return isSubOrSuperClass(otherField)
                && value.equals(((StringField) other).value);
    }

}
