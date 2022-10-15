package seedu.nutrigoals.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.nutrigoals.commons.exceptions.IllegalValueException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.NutriGoals;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.user.User;

/**
 * An Immutable NutriGoals that is serializable to JSON format.
 */
@JsonRootName(value = "nutrigoals")
class JsonSerializableNutriGoals {

    public static final String MESSAGE_DUPLICATE_FOOD = "foods list contains duplicate food(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();
    private final Calorie calorieTarget;
    private final User user;
    private final List<Location> nusGymsLocations;
    /**
     * Constructs a {@code JsonSerializableNutriGoals} with the given foods.
     */
    // This constructor is for unit tests
    @JsonCreator
    public JsonSerializableNutriGoals(@JsonProperty("foods") List<JsonAdaptedFood> foods,
                                      @JsonProperty("nusGymsLocations") List<Location> nusGymsLocations) {
        this.foods.addAll(foods);
        this.calorieTarget = new Calorie();
        this.user = new User();
        this.nusGymsLocations = nusGymsLocations;
    }

    /**
     * Converts a given {@code ReadOnlyNutriGoals} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNutriGoals}.
     */
    public JsonSerializableNutriGoals(ReadOnlyNutriGoals source) {
        foods.addAll(source.getFoodList().stream().map(JsonAdaptedFood::new).collect(Collectors.toList()));
        calorieTarget = source.getCalorieTarget();
        user = source.getUser();
        nusGymsLocations = source.getGymLocations();
    }

    /**
     * Converts this food list into the model's {@code NutriGoals} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NutriGoals toModelType() throws IllegalValueException {
        NutriGoals nutriGoals = new NutriGoals();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (nutriGoals.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD);
            }
            nutriGoals.addFood(food);
        }
        nutriGoals.setCalorieTarget(this.calorieTarget);
        nutriGoals.setUser(user);
        nutriGoals.setNusGymsLocations(this.nusGymsLocations);
        return nutriGoals;
    }

}
