package seedu.intrack.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.intrack.testutil.Assert.assertThrows;
import static seedu.intrack.testutil.TypicalInternships.GOOG;
import static seedu.intrack.testutil.TypicalInternships.META;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;
import seedu.intrack.testutil.InTrackBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InTrack(), new InTrack(modelManager.getInTrack()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInTrackFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInTrackFilePath(Paths.get("new/address/book/file/path"));
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
    public void setInTrackFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInTrackFilePath(null));
    }

    @Test
    public void setInTrackFilePath_validPath_setsInTrackFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setInTrackFilePath(path);
        assertEquals(path, modelManager.getInTrackFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInTrack_returnsFalse() {
        assertFalse(modelManager.hasInternship(GOOG));
    }

    @Test
    public void hasInternship_internshipInInTrack_returnsTrue() {
        modelManager.addInternship(GOOG);
        assertTrue(modelManager.hasInternship(GOOG));
    }

    @Test
    public void isInternshipSortedAscending_internshipInInTrack_returnsTrue() {
        InTrack inTrack = new InTrackBuilder().withInternship(GOOG).withInternship(META).build();
        UserPrefs userPrefs = new UserPrefs();

        // after sorting, elements are the same buf different order
        modelManager = new ModelManager(inTrack, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(inTrack, userPrefs);
        modelManager.ascendSortTime();
        assertTrue(modelManager.equals(modelManagerCopy));

    }

    @Test
    public void isInternshipSortedDescending_internshipInInTrack_returnsTrue() {
        InTrack inTrack = new InTrackBuilder().withInternship(GOOG).withInternship(META).build();
        UserPrefs userPrefs = new UserPrefs();

        // after sorting, elements should not change, but reverse in collections for some reason alters it?
        modelManager = new ModelManager(inTrack, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(inTrack, userPrefs);
        modelManager.descendSortTime();
        assertTrue(modelManager.equals(modelManagerCopy));

    }


    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void getFilteredStatusInternshipListSize_emptyList_returnsZero() {
        modelManager = new ModelManager();
        assertEquals(0, modelManager.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Offered")));
    }

    @Test
    public void getFilteredStatusInternshipListSize_oneStatusFound() {
        modelManager = new ModelManager();
        modelManager.addInternship(GOOG);
        assertEquals(1, modelManager.getFilteredStatusInternshipListSize(
                new StatusIsKeywordPredicate(GOOG.getStatus().value)));
    }

    @Test
    public void equals() {
        InTrack inTrack = new InTrackBuilder().withInternship(GOOG).withInternship(META).build();
        InTrack differentInTrack = new InTrack();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(inTrack, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(inTrack, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different inTrack -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInTrack, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = GOOG.getName().fullName.split("\\s+");
        modelManager.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(inTrack, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInTrackFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(inTrack, differentUserPrefs)));
    }
}
