package eatwhere.foodguide.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.model.AddressBook;
import eatwhere.foodguide.model.ReadOnlyAddressBook;
import eatwhere.foodguide.model.person.Person;

/**
 * An Immutable FoodGuide that is serializable to JSON format.
 */
@JsonRootName(value = "foodguide")
class JsonSerializableFoodGuide {

    public static final String MESSAGE_DUPLICATE_EATERY = "Eateries list contains duplicate(s).";

    private final List<JsonAdaptedEatery> eateries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFoodGuide} with the given eateries.
     */
    @JsonCreator
    public JsonSerializableFoodGuide(@JsonProperty("eateries") List<JsonAdaptedEatery> eateries) {
        this.eateries.addAll(eateries);
    }

    /**
     * Converts a given {@code ReadOnlyFoodGuide} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFoodGuide}.
     */
    public JsonSerializableFoodGuide(ReadOnlyAddressBook source) {
        eateries.addAll(source.getPersonList().stream().map(JsonAdaptedEatery::new).collect(Collectors.toList()));
    }

    /**
     * Converts this food guide into the model's {@code FoodGuide} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook foodGuide = new AddressBook();
        for (JsonAdaptedEatery jsonAdaptedEatery : eateries) {
            Person eatery = jsonAdaptedEatery.toModelType();
            if (foodGuide.hasPerson(eatery)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EATERY);
            }
            foodGuide.addPerson(eatery);
        }
        return foodGuide;
    }

}
