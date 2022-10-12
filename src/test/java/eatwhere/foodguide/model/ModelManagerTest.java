package eatwhere.foodguide.model;

import static eatwhere.foodguide.model.Model.PREDICATE_SHOW_ALL_EATERIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.FoodGuideBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FoodGuide(), new FoodGuide(modelManager.getFoodGuide()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFoodGuideFilePath(Paths.get("food/guide/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFoodGuideFilePath(Paths.get("new/food/guide/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setFoodGuideFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setFoodGuideFilePath(null));
    }

    @Test
    public void setFoodGuideFilePath_validPath_setsFoodGuideFilePath() {
        Path path = Paths.get("food/guide/file/path");
        modelManager.setFoodGuideFilePath(path);
        assertEquals(path, modelManager.getFoodGuideFilePath());
    }

    @Test
    public void hasEatery_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasEatery(null));
    }

    @Test
    public void hasEatery_eateryNotInFoodGuide_returnsFalse() {
        assertFalse(modelManager.hasEatery(TypicalEateries.ALICE));
    }

    @Test
    public void hasEatery_eateryInFodoGuide_returnsTrue() {
        modelManager.addEatery(TypicalEateries.ALICE);
        assertTrue(modelManager.hasEatery(TypicalEateries.ALICE));
    }

    @Test
    public void getFilteredEateryList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEateryList().remove(0));
    }

    @Test
    public void equals() {
        FoodGuide foodGuide = new FoodGuideBuilder()
                .withEatery(TypicalEateries.ALICE).withEatery(TypicalEateries.BENSON).build();
        FoodGuide differentFoodGuide = new FoodGuide();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(foodGuide, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(foodGuide, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different foodGuide -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFoodGuide, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalEateries.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEateryList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(foodGuide, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEateryList(PREDICATE_SHOW_ALL_EATERIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFoodGuideFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(foodGuide, differentUserPrefs)));
    }
}
