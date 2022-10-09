package seedu.nutrigoals.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nutrigoals.commons.exceptions.IllegalValueException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Food}.
 */
class JsonAdaptedFood {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meal's %s field is missing!";

    private final String name;
    private final String calorie;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedFood} with the given food details.
     */
    @JsonCreator
    public JsonAdaptedFood(@JsonProperty("foodName") String foodName, @JsonProperty("calorie") String calorie,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("dateTime") String dateTime) {
        this.name = foodName;
        this.calorie = calorie;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Food} into this class for Jackson use.
     */
    public JsonAdaptedFood(Food source) {
        name = source.getName().fullName;
        calorie = source.getCalorie().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        dateTime = source.getDateTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted food object into the model's {@code Food} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted food.
     */
    public Food toModelType() throws IllegalValueException {
        final List<Tag> mealTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            mealTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (!Calorie.isValidCalorie(calorie)) {
            throw new IllegalValueException(Calorie.MESSAGE_CONSTRAINTS);
        }
        final Calorie modelCalorie = new Calorie(calorie);

        final Set<Tag> modelTags = new HashSet<>(mealTags);
        final DateTime modelDateTime = new DateTime(dateTime);
        return new Food(modelName, modelCalorie, modelTags, modelDateTime);
    }

}
