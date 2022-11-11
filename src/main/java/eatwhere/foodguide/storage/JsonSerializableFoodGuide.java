package eatwhere.foodguide.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import eatwhere.foodguide.commons.exceptions.IllegalValueException;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;

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
    public JsonSerializableFoodGuide(ReadOnlyFoodGuide source) {
        eateries.addAll(source.getEateryList().stream().map(JsonAdaptedEatery::new).collect(Collectors.toList()));
    }

    /**
     * Converts this food guide into the model's {@code FoodGuide} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodGuide toModelType() throws IllegalValueException {
        FoodGuide foodGuide = new FoodGuide();
        for (JsonAdaptedEatery jsonAdaptedEatery : eateries) {
            Eatery eatery = jsonAdaptedEatery.toModelType();
            if (foodGuide.hasEatery(eatery)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EATERY);
            }
            foodGuide.addEatery(eatery);
        }
        return foodGuide;
    }

}
