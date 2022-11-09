package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.filterListByFoodName;
import static seedu.nutrigoals.model.Model.PREDICATE_SHOW_ALL_FOODS;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.BREAD;
import static seedu.nutrigoals.testutil.TypicalFoods.MAX_CALORIE_FOOD;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import seedu.nutrigoals.commons.core.GuiSettings;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.NutriGoalsBuilder;
import seedu.nutrigoals.testutil.UserBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();
    private ModelManager typicalModelManager = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

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
    public void isUserCreated_userNotCreated_returnsFalse() {
        assertFalse(modelManager.isUserCreated());
    }

    @Test
    public void isUserCreated_userCreated_returnsTrue() {
        User validUser = new UserBuilder().build();
        modelManager.setUserDetails(validUser);
        assertTrue(modelManager.isUserCreated());
    }

    @Test
    public void setUserDetails_nullUser_throwsException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserDetails(null));
    }

    @Test
    public void getCalorieDifference() {
        assertEquals(-12000, typicalModelManager.getCalorieDifference());
    }

    @Test
    public void getTotalCalorie() {
        assertEquals(new Calorie("14000"), typicalModelManager.getTotalCalorie());
    }

    @Test
    public void isAddedTotalCalorieTooLarge_notTooLarge_returnFalse() {
        assertFalse(typicalModelManager.isAddedTotalCalorieTooLarge(APPLE));
    }

    @Test
    public void isAddedTotalCalorieTooLarge_tooLarge_returnTrue() {
        assertTrue(typicalModelManager.isAddedTotalCalorieTooLarge(MAX_CALORIE_FOOD));
    }

    @Test
    public void isEditedTotalCalorieTooLarge_notTooLarge_returnFalse() {
        assertFalse(typicalModelManager.isEditedTotalCalorieTooLarge(APPLE, BREAD));
    }

    @Test
    public void isEditedTotalCalorieTooLarge_tooLarge_returnTrue() {
        assertTrue(typicalModelManager.isEditedTotalCalorieTooLarge(MAX_CALORIE_FOOD, APPLE));
    }

    @Test
    public void getFoodCalorieList() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFoodCalorieList().clear());
    }

    @Test
    public void calculateCalorieIntakeProgress() {
        ModelManager modelManager = new ModelManager();
        modelManager.addFood(APPLE);
        modelManager.setCalorieTarget(new Calorie("2500"));
        assertEquals(0.8, modelManager.calculateCalorieIntakeProgress());
    }

    @Test
    public void getCalorieIntakeProgress() {
        DoubleProperty expectedProgress = new SimpleDoubleProperty(2.0);
        ModelManager modelManager = new ModelManager();
        modelManager.addFood(APPLE);
        modelManager.setCalorieTarget(new Calorie("1000"));
        assertEquals(expectedProgress.get(), modelManager.getCalorieIntakeProgress().get());
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
        filterListByFoodName(modelManager, APPLE);
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
