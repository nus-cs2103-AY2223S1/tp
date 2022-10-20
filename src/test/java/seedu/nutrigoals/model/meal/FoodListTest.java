package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.PANCAKE;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.meal.exceptions.MealNotFoundException;
import seedu.nutrigoals.testutil.FoodBuilder;

public class FoodListTest {

    private final FoodList foodList = new FoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(foodList.contains(APPLE));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        foodList.add(APPLE);
        assertTrue(foodList.contains(APPLE));
    }

    @Test
    public void contains_foodWithSameIdentityFieldsInList_returnsTrue() {
        foodList.add(APPLE);
        Food editedApple = new FoodBuilder(APPLE).withTag(VALID_TAG_LUNCH).build();
        assertTrue(foodList.contains(editedApple));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.add(null));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood(null, APPLE));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood(APPLE, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(MealNotFoundException.class, () -> foodList.setFood(APPLE, APPLE));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        foodList.add(APPLE);
        foodList.setFood(APPLE, APPLE);
        FoodList expectedFoodList = new FoodList();
        expectedFoodList.add(APPLE);
        assertEquals(expectedFoodList, foodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        foodList.add(APPLE);
        Food editedAlice = new FoodBuilder(APPLE).withTag(VALID_TAG_BREAKFAST).build();
        foodList.setFood(APPLE, editedAlice);
        FoodList expectedFoodList = new FoodList();
        expectedFoodList.add(editedAlice);
        assertEquals(expectedFoodList, foodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        foodList.add(APPLE);
        foodList.setFood(APPLE, PANCAKE);
        FoodList expectedFoodList = new FoodList();
        expectedFoodList.add(PANCAKE);
        assertEquals(expectedFoodList, foodList);
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(MealNotFoundException.class, () -> foodList.remove(APPLE));
    }

    @Test
    public void remove_existingFood_removesFood() {
        foodList.add(APPLE);
        foodList.remove(APPLE);
        FoodList expectedFoodList = new FoodList();
        assertEquals(expectedFoodList, foodList);
    }

    @Test
    public void setFoods_nullFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood((FoodList) null));
    }

    @Test
    public void setFoods_foodList_replacesOwnListWithProvidedFoodList() {
        foodList.add(APPLE);
        FoodList expectedFoodList = new FoodList();
        expectedFoodList.add(PANCAKE);
        foodList.setFood(expectedFoodList);
        assertEquals(expectedFoodList, foodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        foodList.add(APPLE);
        List<Food> foodList = Collections.singletonList(PANCAKE);
        this.foodList.setFood(foodList);
        FoodList expectedFoodList = new FoodList();
        expectedFoodList.add(PANCAKE);
        assertEquals(expectedFoodList, this.foodList);
    }


    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> foodList.asUnmodifiableObservableList().remove(0));
    }
}
