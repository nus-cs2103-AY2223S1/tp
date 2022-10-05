package seedu.address.model.order;

import java.util.Objects;

import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.Pattern;
import seedu.address.model.pet.Species;

public class Request {

    private final Age age;
    private final Color color;
    private final Pattern pattern;
    private final Species species;

    public Request(Age age, Color color, Pattern pattern,
                   Species species) {
        this.age = age;
        this.color = color;
        this.pattern = pattern;
        this.species = species;
    }

    public Age getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public Pattern getPattern() {
        return pattern;
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
                    && pattern.equals(otherRequest.getPattern())
                    && species.equals(otherRequest.getSpecies());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, color, pattern, species);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pet age: ").append(getAge())
                .append(System.lineSeparator())
                .append("Color: ").append(getColor())
                .append(System.lineSeparator())
                .append("Pattern: ").append(getPattern())
                .append(System.lineSeparator())
                .append("Species: ").append(getSpecies());
        return builder.toString();
    }

}
