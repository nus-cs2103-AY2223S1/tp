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

    private final Age requestedAge;
    private final Color requestedColor;
    private final ColorPattern requestedColorPattern;
    private final Species requestedSpecies;

    /**
     * Constructs a {@code request}.
     *
     * @param requestedAge Age of the pet.
     * @param requestedColor Color of the pet.
     * @param requestedColorPattern Color pattern of the pet.
     * @param requestedSpecies Species the pet belongs to.
     */
    public Request(Age requestedAge,
                   Color requestedColor,
                   ColorPattern requestedColorPattern,
                   Species requestedSpecies) {
        this.requestedAge = requestedAge;
        this.requestedColor = requestedColor;
        this.requestedColorPattern = requestedColorPattern;
        this.requestedSpecies = requestedSpecies;
    }

    public Age getRequestedAge() {
        return requestedAge;
    }

    public Color getRequestedColor() {
        return requestedColor;
    }

    public ColorPattern getRequestedColorPattern() {
        return requestedColorPattern;
    }

    public Species getRequestedSpecies() {
        return requestedSpecies;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Request) {
            Request otherRequest = (Request) other;
            return requestedAge.equals(otherRequest.getRequestedAge())
                    && requestedColor.equals(otherRequest.getRequestedColor())
                    && requestedColorPattern.equals(otherRequest.getRequestedColorPattern())
                    && requestedSpecies.equals(otherRequest.getRequestedSpecies());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedAge, requestedColor, requestedColorPattern, requestedSpecies);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pet age: ").append(getRequestedAge())
                .append(System.lineSeparator())
                .append("Color: ").append(getRequestedColor())
                .append(System.lineSeparator())
                .append("Pattern: ").append(getRequestedColorPattern())
                .append(System.lineSeparator())
                .append("Species: ").append(getRequestedSpecies());
        return builder.toString();
    }

}
