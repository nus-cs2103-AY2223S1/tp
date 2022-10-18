package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.order.Request;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;

/**
 * Jackson-friendly version of {@link Request}.
 */
public class JsonAdaptedRequest {

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
    public Request toModelType() {
        //TODO validate the data before converting
        Age modelAge = new Age(age);
        Color modelColor = new Color(color);
        ColorPattern modelColorPattern = new ColorPattern(colorPattern);
        Species modelSpecies = new Species(species);
        return new Request(modelAge, modelColor, modelColorPattern, modelSpecies);
    }
}
