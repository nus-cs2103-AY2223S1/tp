package seedu.address.model.pet;

public class Color {
    private final String color;

    public Color(String color) {
        if (color == null) {
            this.color = "";
        } else {
            this.color = color;
        }
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
        return color;
    }

    public String getColor() {
        return color;
    }
}
