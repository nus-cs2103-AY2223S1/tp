package eatwhere.foodguide.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Price;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Eatery}.
 */
class JsonAdaptedEatery {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Eatery's %s field is missing!";

    private final String name;
    private final String price;
    private final String cuisine;
    private final String location;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEatery} with the given eatery details.
     */
    @JsonCreator
    public JsonAdaptedEatery(@JsonProperty("name") String name, @JsonProperty("price") String price,
                             @JsonProperty("cuisine") String cuisine, @JsonProperty("location") String location,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
        this.location = location;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Eatery} into this class for Jackson use.
     */
    public JsonAdaptedEatery(Eatery source) {
        name = source.getName().fullName;
        price = source.getPrice().value;
        cuisine = source.getCuisine().value;
        location = source.getLocation().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted eatery object into the model's {@code Eatery} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted eatery.
     */
    public Eatery toModelType() throws IllegalValueException {
        final List<Tag> eateryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            eateryTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        if (cuisine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cuisine.class.getSimpleName()));
        }
        if (!Cuisine.isValidCuisine(cuisine)) {
            throw new IllegalValueException(Cuisine.MESSAGE_CONSTRAINTS);
        }
        final Cuisine modelCuisine = new Cuisine(cuisine);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        final Set<Tag> modelTags = new HashSet<>(eateryTags);
        return new Eatery(modelName, modelPrice, modelCuisine, modelLocation, modelTags);
    }

}
