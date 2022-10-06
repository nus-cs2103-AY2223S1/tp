package seedu.address.model.pet;

public class ColorPattern {

    private final String colorPattern;

    public ColorPattern(String colorPattern) {
        if (colorPattern == null) {
            this.colorPattern = "";
        } else {
            this.colorPattern = colorPattern;
        }
    }

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
