package seedu.foodrem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.foodrem.testutil.Assert.assertThrows;
import static seedu.foodrem.testutil.TypicalItems.CUCUMBERS;
import static seedu.foodrem.testutil.TypicalItems.POTATOES;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodrem.commons.core.GuiSettings;
import seedu.foodrem.model.item.NameContainsKeywordsPredicate;
import seedu.foodrem.testutil.FoodRemBuilder;

/**
 * A class to test the ModelManager.
 */
public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FoodRem(), new FoodRem(modelManager.getFoodRem()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFoodRemFilePath(Paths.get("food/rem/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFoodRemFilePath(Paths.get("new/food/rem/file/path"));
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
    public void setFoodRemFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFoodRemFilePath(null));
    }

    @Test
    public void setFoodRemFilePath_validPath_setsFoodRemFilePath() {
        Path path = Paths.get("food/rem/file/path");
        modelManager.setFoodRemFilePath(path);
        assertEquals(path, modelManager.getFoodRemFilePath());
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInFoodRem_returnsFalse() {
        assertFalse(modelManager.hasItem(POTATOES));
    }

    @Test
    public void hasItem_itemInFoodRem_returnsTrue() {
        modelManager.addItem(POTATOES);
        assertTrue(modelManager.hasItem(POTATOES));
    }

    @Test
    public void getCurrentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getCurrentList().remove(0));
    }

    @Test
    public void equals() {
        FoodRem foodRem = new FoodRemBuilder().withItem(POTATOES).withItem(CUCUMBERS).build();
        FoodRem differentFoodRem = new FoodRem();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(foodRem, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(foodRem, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different foodRem -> returns false
        assertNotEquals(modelManager, new ModelManager(differentFoodRem, userPrefs));

        // different filteredList -> returns false
        String keywords = String.valueOf(POTATOES.getName());
        modelManager.updateFilteredItemList(new NameContainsKeywordsPredicate(List.of(keywords)));
        assertNotEquals(modelManager, new ModelManager(foodRem, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFoodRemFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(foodRem, differentUserPrefs));
    }
}
