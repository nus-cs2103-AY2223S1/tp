package seedu.address.model.attribute;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's custom field in the address book. Guarantees: immutable; is valid as
 * declared in {@link #isValidField(String)}
 */
public class Field {

    public static final String MESSAGE_CONSTRAINTS = "Custom fields can take in any values, and it should not be blank";

    /*
     * The first character of the field must not be a whitespace, otherwise " " (a blank string) becomes
     * a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String name;
    public final String value;

    /**
     * Constructs a {@code Field}.
     *
     * @param name The name of the field.
     */
    public Field(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.value = null;
    }

    /**
     * Constructs a {@code Field}.
     *
     * @param name The name of the field.
     * @param value The value of the field.
     */
    public Field(String name, String value) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        requireNonNull(value);
        checkArgument(isValidField(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Sets the value of a custom Field by returning a new Field containing the field name and the new
     * value to preserve immutability
     *
     * @param value The value to be set to the field
     * @return A new Field instance containing the name and the specified value
     */
    public Field setValue(String value) {
        return new Field(name, value);
    }

    /**
     * Checks if the value of the Field has been set
     *
     * @return true if the value of the field is not null, false otherwise
     */
    public boolean isValueSet() {
        return value == null;
    }

    /**
     * Returns true if a given string is a valid name argument.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid field argument.
     */
    public static boolean isValidField(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isNameMatch(String test) {
        return test.equalsIgnoreCase(name);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + name + "," + value + "]";
    }

    /**
     * Returns a string representation to be displayed in the {@code PersonCard}
     *
     * @return the string representation for display
     */
    public String toStringDisplay() {
        if (value == null) {
            return name + ": ";
        }
        return name + ": " + value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Field) {
            Field castedOther = (Field) other;
            if (castedOther.value == null && value == null) {
                return castedOther.isNameMatch(name);
            } else if (castedOther.value == null || value == null) {
                return false;
            }
            return name.equalsIgnoreCase(castedOther.name) && value.equals(castedOther.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
