package seedu.trackascholar.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.trackascholar.model.Model.PREDICATE_SHOW_PINNED_APPLICANTS;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.testutil.TrackAScholarBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TrackAScholar(), new TrackAScholar(modelManager.getTrackAScholar()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackAScholarFilePath(Paths.get("trackascholar/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackAScholarFilePath(Paths.get("new/trackascholar/book/file/path"));
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
    public void setTrackAScholarFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackAScholarFilePath(null));
    }

    @Test
    public void setTrackAScholarFilePath_validPath_setsTrackAScholarFilePath() {
        Path path = Paths.get("trackascholar/book/file/path");
        modelManager.setTrackAScholarFilePath(path);
        assertEquals(path, modelManager.getTrackAScholarFilePath());
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInTrackAScholar_returnsFalse() {
        assertFalse(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInTrackAScholar_returnsTrue() {
        modelManager.addApplicant(ALICE);
        assertTrue(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicantList().remove(0));
    }

    @Test
    public void getPinnedApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getPinnedApplicantList().remove(0));
    }

    @Test
    public void getAllApplicants_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getAllApplicants().remove(0));
    }

    @Test
    public void equals() {
        TrackAScholar trackAScholar = new TrackAScholarBuilder().withApplicant(ALICE).withApplicant(BENSON).build();
        TrackAScholar differentTrackAScholar = new TrackAScholar();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackAScholar, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackAScholar, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different trackAScholar -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackAScholar, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getFullName().split("\\s+");
        modelManager.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(trackAScholar, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackAScholarFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackAScholar, differentUserPrefs)));
    }
}
