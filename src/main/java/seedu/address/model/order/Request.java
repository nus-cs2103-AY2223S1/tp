package seedu.address.model.order;

import java.util.Objects;

import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

public class Request {

    private final Age age;
    private final Color color;
    private final ColorPattern colorPattern;
    private final Species species;

    public Request(Age age, Color color, ColorPattern colorPattern,
                   Species species) {
        this.age = age;
        this.color = color;
        this.colorPattern = colorPattern;
        this.species = species;
    }

    public Age getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public ColorPattern getColorPattern() {
        return colorPattern;
    }

    public Species getSpecies() {
        return species;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Request) {
            Request otherRequest = (Request) other;
            return age.equals(otherRequest.getAge())
                    && color.equals(otherRequest.getColor())
                    && colorPattern.equals(otherRequest.getColorPattern())
                    && species.equals(otherRequest.getSpecies());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, color, colorPattern, species);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pet age: ").append(getAge())
                .append(System.lineSeparator())
                .append("Color: ").append(getColor())
                .append(System.lineSeparator())
                .append("Pattern: ").append(getColorPattern())
                .append(System.lineSeparator())
                .append("Species: ").append(getSpecies());
        return builder.toString();
    }

}
