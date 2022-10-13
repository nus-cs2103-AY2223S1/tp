package seedu.address.model.pet;

import java.util.Objects;

/**
 * Abstracts the color of a pet.
 */
public class Color {
    private final String color;

    /**
     * Constructs a color object.
     *
     * @param color The string representation of a color.
     */
    public Color(String color) {
        this.color = Objects.requireNonNullElse(color, "");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Color // instanceof handles nulls
                && color.equals(((Color) other).color)); // state check
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return "Color: " + color;
    }

    /**
     * Gets the color of a pet.
     *
     * @return The color.
     */
    public String getColor() {
        return color;
    }
}
