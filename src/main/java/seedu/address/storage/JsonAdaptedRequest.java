package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

/**
 * Jackson-friendly version of {@link Request}.
 */
public class JsonAdaptedRequest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Request's %s field is missing!";

    private final int age;
    private final String color;
    private final String colorPattern;
    private final String species;

    /**
     * Constructs a {@code JsonAdaptedRequest} with the given request details.
     */
    public JsonAdaptedRequest(@JsonProperty("age") int age, @JsonProperty("color") String color,
                              @JsonProperty("colorPattern") String colorPattern,
                              @JsonProperty("species") String species) {
        this.age = age;
        this.color = color;
        this.colorPattern = colorPattern;
        this.species = species;
    }

    /**
     * Converts a given {@code Request} into this class for Jackson use.
     */
    public JsonAdaptedRequest(Request request) {
        this.age = request.getRequestedAge().getValue();
        this.color = request.getRequestedColor().getValue();
        this.colorPattern = request.getRequestedColorPattern().getValue();
        this.species = request.getRequestedSpecies().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted Request object into the model's {@code Request} object.
     */
    public Request toModelType() throws IllegalValueException {
        if (age < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getName()));
        }

        if (color == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Color.class.getName()));
        }
        if (colorPattern == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ColorPattern.class.getName()));
        }

        if (species == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getName()));
        }

        Age modelAge = new Age(age);
        Color modelColor = new Color(color);
        ColorPattern modelColorPattern = new ColorPattern(colorPattern);
        Species modelSpecies = new Species(species);
        return new Request(modelAge, modelColor, modelColorPattern, modelSpecies);
    }
}
