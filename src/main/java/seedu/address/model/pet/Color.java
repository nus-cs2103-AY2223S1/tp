package seedu.address.model.pet;

/**
 * Abstracts the color of a pet.
 */
public class Color implements Comparable<Color> {

    public static final String MESSAGE_CONSTRAINTS =
            "Colors should only contain alphanumeric characters and spaces, and it should not be blank";
    private final String value;

    /**
     * Constructs a color object.
     *
     * @param value The string representation of a color.
     */
    public Color(String value) {
        this.value = (value == null) || !value.matches("[a-zA-Z][a-zA-Z ]+") ? "" : value;
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

    @Override
    public int compareTo(Color o) {
        return this.value.compareTo(o.getValue());
    }
}
