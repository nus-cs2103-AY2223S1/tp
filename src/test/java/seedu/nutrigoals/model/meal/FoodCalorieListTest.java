package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Calorie;

public class FoodCalorieListTest {
    private final FoodCalorieList foodCalorieList = new FoodCalorieList();

    @Test
    public void setFoodCalorieMapping_list_replacesOwnListWithProvidedList() {
        Map<Name, Calorie> expectedMapping = new HashMap<>();
        expectedMapping.put(new Name("pizza"), new Calorie("299"));

        FoodCalorieList foodCalorieList = new FoodCalorieList();
        foodCalorieList.setFoodCalorieMapping(expectedMapping);
        assertEquals(expectedMapping, foodCalorieList.asUnmodifiableMap());
    }

    @Test
    public void asUnmodifiableMap_modifyMap_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodCalorieList.asUnmodifiableMap().clear());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(foodCalorieList.equals(foodCalorieList));

        // same values -> returns true
        FoodCalorieList foodCalorieListCopy = new FoodCalorieList();
        assertTrue(foodCalorieList.equals(foodCalorieListCopy));

        // different types -> returns false
        assertFalse(foodCalorieList.equals(1));

        // null -> returns false
        assertFalse(foodCalorieList.equals(null));

        // different values -> returns false
        FoodCalorieList anotherFoodCalorieList = new FoodCalorieList();
        Map<Name, Calorie> map = new HashMap<>();
        map.put(new Name("pizza"), new Calorie("299"));
        anotherFoodCalorieList.setFoodCalorieMapping(map);
        assertFalse(foodCalorieList.equals(anotherFoodCalorieList));
    }
}
