package seedu.address.model.pet;

import java.util.Objects;

/**
 * Abstracts the color of a pet.
 */
public class Color {
    private final String value;

    /**
     * Constructs a color object.
     *
     * @param value The string representation of a color.
     */
    public Color(String value) {
        this.value = Objects.requireNonNullElse(value, "");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Color // instanceof handles nulls
                && value.equals(((Color) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Color: " + value;
    }

    /**
     * Gets the color of a pet.
     *
     * @return The color.
     */
    public String getValue() {
        return value;
    }
}
