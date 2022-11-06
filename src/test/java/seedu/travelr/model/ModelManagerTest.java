package seedu.travelr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.testutil.Assert.assertThrows;
import static seedu.travelr.testutil.TypicalTrips.PLUTO;
import static seedu.travelr.testutil.TypicalTrips.SUN;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.model.trip.TitleContainsKeywordsPredicate;
import seedu.travelr.testutil.TravelrBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Travelr(), new Travelr(modelManager.getTravelr()));
    }


    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTravelrFilePath(Paths.get("travelr/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTravelrFilePath(Paths.get("new/travelr/book/file/path"));
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
    public void setTravelrFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTravelrFilePath(null));
    }

    @Test
    public void setTravelrFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("travelr/book/file/path");
        modelManager.setTravelrFilePath(path);
        assertEquals(path, modelManager.getTravelrFilePath());
    }


    @Test
    public void hasTrip_nullTrip_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTrip(null));
    }

    @Test
    public void hasTrip_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTrip(PLUTO));
    }

    @Test
    public void hasTrip_personInAddressBook_returnsTrue() {
        modelManager.addTrip(PLUTO);
        assertTrue(modelManager.hasTrip(PLUTO));
    }

    @Test
    public void getFilteredTripList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTripList().remove(0));
    }

    @Test
    public void equals() {
        Travelr travelr = new TravelrBuilder().withTrip(PLUTO).withTrip(SUN).build();
        Travelr differentTravelr = new Travelr();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(travelr, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(travelr, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTravelr, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = PLUTO.getTitle().fullTitle.split("\\s+");
        modelManager.updateFilteredTripList(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(travelr, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTripList(Model.PREDICATE_SHOW_ALL_TRIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTravelrFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(travelr, differentUserPrefs)));
    }


    @Test
    void testFunction() {
        modelManager.refreshSummaryVariables();
        assertTrue(true);
    }
}
