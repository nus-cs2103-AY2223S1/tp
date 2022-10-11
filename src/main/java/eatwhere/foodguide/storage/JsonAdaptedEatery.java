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
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Eatery}.
 */
class JsonAdaptedEatery {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Eatery's %s field is missing!";

    private final String name;
    private final String phone;
    private final String cuisine;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEatery} with the given eatery details.
     */
    @JsonCreator
    public JsonAdaptedEatery(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String cuisine, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.cuisine = cuisine;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Eatery} into this class for Jackson use.
     */
    public JsonAdaptedEatery(Eatery source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        cuisine = source.getCuisine().value;
        address = source.getLocation().value;
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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (cuisine == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cuisine.class.getSimpleName()));
        }
        if (!Cuisine.isValidCuisine(cuisine)) {
            throw new IllegalValueException(Cuisine.MESSAGE_CONSTRAINTS);
        }
        final Cuisine modelCuisine = new Cuisine(cuisine);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(address)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(address);

        final Set<Tag> modelTags = new HashSet<>(eateryTags);
        return new Eatery(modelName, modelPhone, modelCuisine, modelLocation, modelTags);
    }

}
