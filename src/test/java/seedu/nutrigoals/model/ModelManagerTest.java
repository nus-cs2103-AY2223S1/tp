package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.BREAD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.meal.NameContainsKeywordsPredicate;
import seedu.nutrigoals.testutil.NutriGoalsBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new NutriGoals(), new NutriGoals(modelManager.getNutriGoals()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setNutriGoalsFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setNutriGoalsFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setNutriGoalsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setNutriGoalsFilePath(null));
    }

    @Test
    public void setNutriGoalsFilePath_validPath_setsNutriGoalsFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setNutriGoalsFilePath(path);
        assertEquals(path, modelManager.getNutriGoalsFilePath());
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInNutriGoals_returnsFalse() {
        assertFalse(modelManager.hasFood(APPLE));
    }

    @Test
    public void hasFood_foodInNutriGoals_returnsTrue() {
        modelManager.addFood(APPLE);
        assertTrue(modelManager.hasFood(APPLE));
    }

    @Test
    public void getFilteredFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredFoodList().remove(0));
    }

    @Test
    public void getDatePredicate_filterListForADay_returnsPredicateWithSameDay() {
        DateTime dateTime = new DateTime();
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(dateTime);
        modelManager.updateFilteredFoodList(predicate);
        assertEquals(predicate, modelManager.getDatePredicate());
    }

    @Test
    public void isFilteredFoodListEmpty_emptyFilteredList_returnsTrue() {
        modelManager.updateFilteredFoodList(unused -> false);
        assertTrue(modelManager.isFilteredFoodListEmpty());
    }

    @Test
    public void isFilteredFoodListEmpty_nonEmptyFilteredList_returnsFalse() {
        modelManager.addFood(APPLE);
        modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        assertFalse(modelManager.isFilteredFoodListEmpty());
    }

    @Test
    public void equals() {
        NutriGoals nutriGoals = new NutriGoalsBuilder().withFood(APPLE).withFood(BREAD).build();
        NutriGoals differentNutriGoals = new NutriGoals();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(nutriGoals, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(nutriGoals, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different nutriGoals -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentNutriGoals, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = APPLE.getName().fullName.split("\\s+");
        modelManager.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(nutriGoals, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setNutriGoalsFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(nutriGoals, differentUserPrefs)));

        // different currentDatePredicate -> returns false
        DateTime dateTime = new DateTime(DEFAULT_EARLIER_TIME);
        modelManager.updateFilteredFoodList(new IsFoodAddedOnThisDatePredicate(dateTime));
        assertFalse(modelManager.equals(new ModelManager(nutriGoals, userPrefs)));
    }
}
