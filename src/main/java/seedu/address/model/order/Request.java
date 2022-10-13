package seedu.address.model.order;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;

import java.util.Objects;

import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

/**
 * Contains some properties of a pet desired in an order.
 */
public class Request {

    public static final String MESSAGE_USAGE = "The usage of a request is as follows: \n"
            + "add-r"
            + PREFIX_PET_SPECIES + "SPECIES "
            + PREFIX_ORDER_AGE + "AGE "
            + PREFIX_PET_COLOR + "COLOR "
            + PREFIX_PET_COLOR_PATTERN + "COLOR_PATTERN \n"
            + Age.MESSAGE_USAGE;

    private final Age age;
    private final Color color;
    private final ColorPattern colorPattern;
    private final Species species;

    /**
     * Constructs a {@code request}.
     *
     * @param age Age of the pet.
     * @param color Color of the pet.
     * @param colorPattern Color pattern of the pet.
     * @param species Species the pet belongs to.
     */
    public Request(Age age,
                   Color color,
                   ColorPattern colorPattern,
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
