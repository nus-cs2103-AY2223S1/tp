package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.PANCAKE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.meal.exceptions.DuplicateMealException;
import seedu.nutrigoals.model.meal.exceptions.MealNotFoundException;
import seedu.nutrigoals.testutil.FoodBuilder;

public class UniqueFoodListTest {

    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_foodNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(APPLE));
    }

    @Test
    public void contains_foodInList_returnsTrue() {
        uniqueFoodList.add(APPLE);
        assertTrue(uniqueFoodList.contains(APPLE));
    }

    @Test
    public void contains_foodWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFoodList.add(APPLE);
        Food editedApple = new FoodBuilder(APPLE).withTag(VALID_TAG_LUNCH).build();
        assertTrue(uniqueFoodList.contains(editedApple));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateFoodException() {
        uniqueFoodList.add(APPLE);
        assertThrows(DuplicateMealException.class, () -> uniqueFoodList.add(APPLE));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(null, APPLE));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(APPLE, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(MealNotFoundException.class, () -> uniqueFoodList.setFood(APPLE, APPLE));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.setFood(APPLE, APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(APPLE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        uniqueFoodList.add(APPLE);
        Food editedAlice = new FoodBuilder(APPLE).withTag(VALID_TAG_BREAKFAST).build();
        uniqueFoodList.setFood(APPLE, editedAlice);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(editedAlice);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.setFood(APPLE, PANCAKE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(PANCAKE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.add(PANCAKE);
        assertThrows(DuplicateMealException.class, () -> uniqueFoodList.setFood(APPLE, PANCAKE));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_foodDoesNotExist_throwsFoodNotFoundException() {
        assertThrows(MealNotFoundException.class, () -> uniqueFoodList.remove(APPLE));
    }

    @Test
    public void remove_existingFood_removesFood() {
        uniqueFoodList.add(APPLE);
        uniqueFoodList.remove(APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood((UniqueFoodList) null));
    }

    @Test
    public void setFoods_uniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        uniqueFoodList.add(APPLE);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(PANCAKE);
        uniqueFoodList.setFood(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood((List<Food>) null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(APPLE);
        List<Food> foodList = Collections.singletonList(PANCAKE);
        uniqueFoodList.setFood(foodList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(PANCAKE);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateMealException.class, () -> uniqueFoodList.setFood(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFoodList.asUnmodifiableObservableList().remove(0));
    }
}
