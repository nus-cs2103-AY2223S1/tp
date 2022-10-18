package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Calorie;

public class FoodCaloriesListTest {
    private final FoodCaloriesList foodCaloriesList = new FoodCaloriesList();

    @Test
    public void setFoodCalorieMapping_list_replacesOwnListWithProvidedList() {
        Map<String, Calorie> expectedMapping = new HashMap<>();
        expectedMapping.put("pizza", new Calorie("299"));

        FoodCaloriesList foodCaloriesList = new FoodCaloriesList();
        foodCaloriesList.setFoodCalorieMapping(expectedMapping);
        assertEquals(expectedMapping, foodCaloriesList.asUnmodifiableMap());
    }

    @Test
    public void asUnmodifiableMap_modifyMap_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodCaloriesList.asUnmodifiableMap().clear());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(foodCaloriesList.equals(foodCaloriesList));

        // same values -> returns true
        FoodCaloriesList foodCaloriesListCopy = new FoodCaloriesList();
        assertTrue(foodCaloriesList.equals(foodCaloriesListCopy));

        // different types -> returns false
        assertFalse(foodCaloriesList.equals(1));

        // null -> returns false
        assertFalse(foodCaloriesList.equals(null));

        // different values -> returns false
        FoodCaloriesList anotherFoodCaloriesList = new FoodCaloriesList();
        Map<String, Calorie> map = new HashMap<>();
        map.put("pizza", new Calorie("299"));
        anotherFoodCaloriesList.setFoodCalorieMapping(map);
        assertFalse(foodCaloriesList.equals(anotherFoodCaloriesList));
    }
}
