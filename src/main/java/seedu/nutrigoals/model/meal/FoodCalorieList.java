package seedu.nutrigoals.model.meal;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.nutrigoals.model.Calorie;

/**
 * Represents a list of common food items and their respective calorie contents for 1 serving.
 */
public class FoodCalorieList {
    private final HashMap<Name, Calorie> foodToCalorieMap = new HashMap<>();
    private final Map<Name, Calorie> internalUnmodifiableMap = Collections.unmodifiableMap(foodToCalorieMap);

    /**
     * Constructs a {@code FoodCalorieList} with a default list of food items and their calories.
     */
    public FoodCalorieList() {
        // https://healthscreening.com.sg/food-calories-chart/
        foodToCalorieMap.put(new Name("ban mian"), new Calorie("475"));
        foodToCalorieMap.put(new Name("bubble tea"), new Calorie("232"));
        foodToCalorieMap.put(new Name("chicken rice"), new Calorie("702"));
        foodToCalorieMap.put(new Name("fried rice"), new Calorie("508"));
        foodToCalorieMap.put(new Name("laksa"), new Calorie("589"));
        foodToCalorieMap.put(new Name("nasi lemak"), new Calorie("600"));
        foodToCalorieMap.put(new Name("potato chips"), new Calorie("184"));
        foodToCalorieMap.put(new Name("rice"), new Calorie("260"));
        foodToCalorieMap.put(new Name("wanton noodles"), new Calorie("409"));
        foodToCalorieMap.put(new Name("white bread"), new Calorie("77"));
    }

    /**
     * Replaces the {@code HashMap} with the given {@code Map}.
     * @param replacement The {@code Map} to replace the current {@code HashMap} with.
     */
    public void setFoodCalorieMapping(Map<Name, Calorie> replacement) {
        requireNonNull(replacement);
        foodToCalorieMap.clear();
        foodToCalorieMap.putAll(replacement);
    }

    /**
     * Returns the {@code HashMap} as an unmodifiable {@code Map}.
     * @returns The {@code HashMap} as an unmodifiable {@code Map}.
     */
    public Map<Name, Calorie> asUnmodifiableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodCalorieList // instanceof handles nulls
                && foodToCalorieMap.equals(((FoodCalorieList) other).foodToCalorieMap));
    }
}
