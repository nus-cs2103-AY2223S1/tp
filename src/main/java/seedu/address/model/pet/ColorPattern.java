package seedu.address.model.pet;

/**
 * A class the represents the color pattern of a pet. For example, stripped, grid, dark.
 */
public class ColorPattern {

    private final String colorPattern;

    /**
     * Constructs a ColorPattern object.
     *
     * @param colorPattern The string representation of color pattern.
     */
    public ColorPattern(String colorPattern) {
        if (colorPattern == null) {
            this.colorPattern = "";
        } else {
            this.colorPattern = colorPattern;
        }
    }

    /**
     * Gets the color pattern of a pet.
     *
     * @return The color pattern.
     */
    public String getColorPattern() {
        return colorPattern;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ColorPattern) {
            ColorPattern otherColorPattern = (ColorPattern) other;
            return colorPattern.equals(otherColorPattern.getColorPattern());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return colorPattern.hashCode();
    }

    @Override
    public String toString() {
        return colorPattern;
    }
}
