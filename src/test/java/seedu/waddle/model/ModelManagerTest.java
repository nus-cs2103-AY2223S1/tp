package seedu.waddle.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.AUTUMN;
import static seedu.waddle.testutil.TypicalItineraries.SPRING;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;
import seedu.waddle.testutil.WaddleBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Waddle(), new Waddle(modelManager.getWaddle()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setWaddleFilePath(Paths.get("waddle/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setWaddleFilePath(Paths.get("new/waddle/file/path"));
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
    public void setWaddleFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setWaddleFilePath(null));
    }

    @Test
    public void setWaddleFilePath_validPath_setsWaddleFilePath() {
        Path path = Paths.get("waddle/file/path");
        modelManager.setWaddleFilePath(path);
        assertEquals(path, modelManager.getWaddleFilePath());
    }

    @Test
    public void hasItinerary_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItinerary(null));
    }

    @Test
    public void hasItinerary_itineraryNotInWaddle_returnsFalse() {
        assertFalse(modelManager.hasItinerary(SPRING));
    }

    @Test
    public void hasItinerary_itineraryInWaddle_returnsTrue() {
        modelManager.addItinerary(SPRING);
        assertTrue(modelManager.hasItinerary(SPRING));
    }

    @Test
    public void getFilteredItineraryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredItineraryList().remove(0));
    }

    @Test
    public void equals() {
        Waddle waddle = new WaddleBuilder().withItinerary(SPRING).withItinerary(AUTUMN).build();
        Waddle differentWaddle = new Waddle();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(waddle, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(waddle, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different waddle -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentWaddle, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = SPRING.getDescription().description.split("\\s+");
        modelManager.updateFilteredItineraryList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(waddle, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setWaddleFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(waddle, differentUserPrefs)));
    }
}
