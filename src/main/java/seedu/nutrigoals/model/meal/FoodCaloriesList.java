package seedu.nutrigoals.model.meal;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.nutrigoals.model.Calorie;

/**
 * Represents a list of common food items and their respective calorie contents for 1 serving.
 */
public class FoodCaloriesList {
    private final HashMap<String, Calorie> foodToCaloriesMap = new HashMap<>();
    private final Map<String, Calorie> internalUnmodifiableMap = Collections.unmodifiableMap(foodToCaloriesMap);

    /**
     * Constructs a {@code FoodCaloriesList} with a default list of food items and their calories.
     */
    public FoodCaloriesList() {
        // https://healthscreening.com.sg/food-calories-chart/
        foodToCaloriesMap.put("ban mian", new Calorie("475"));
        foodToCaloriesMap.put("bubble tea", new Calorie("232"));
        foodToCaloriesMap.put("chicken rice", new Calorie("702"));
        foodToCaloriesMap.put("fried rice", new Calorie("508"));
        foodToCaloriesMap.put("laksa", new Calorie("589"));
        foodToCaloriesMap.put("nasi lemak", new Calorie("600"));
        foodToCaloriesMap.put("potato chips", new Calorie("184"));
        foodToCaloriesMap.put("rice", new Calorie("260"));
        foodToCaloriesMap.put("wanton noodles", new Calorie("409"));
        foodToCaloriesMap.put("white bread", new Calorie("77"));
    }

    /**
     * Replaces the {@code HashMap} with the given {@code Map}.
     * @param replacement The {@code Map} to replace the current {@code HashMap} with.
     */
    public void setFoodCalorieMapping(Map<String, Calorie> replacement) {
        requireNonNull(replacement);
        foodToCaloriesMap.clear();
        foodToCaloriesMap.putAll(replacement);
    }

    /**
     * Returns the {@code HashMap} as an unmodifiable {@code Map}.
     * @returns The {@code HashMap} as an unmodifiable {@code Map}.
     */
    public Map<String, Calorie> asUnmodifiableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodCaloriesList // instanceof handles nulls
                && foodToCaloriesMap.equals(((FoodCaloriesList) other).foodToCaloriesMap));
    }
}
