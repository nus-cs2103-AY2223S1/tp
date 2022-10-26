package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALIBABA;
import static seedu.address.testutil.TypicalInternships.BINANCE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.ContainsKeywordsPredicate;
import seedu.address.testutil.FindMyInternBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FindMyIntern(), new FindMyIntern(modelManager.getFindMyIntern()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFindMyInternFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFindMyInternFilePath(Paths.get("new/address/book/file/path"));
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
    public void setFindMyInternFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFindMyInternFilePath(null));
    }

    @Test
    public void setFindMyInternFilePath_validPath_setsFindMyInternFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFindMyInternFilePath(path);
        assertEquals(path, modelManager.getFindMyInternFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInFindMyIntern_returnsFalse() {
        assertFalse(modelManager.hasInternship(ALIBABA));
    }

    @Test
    public void hasInternship_internshipInFindMyIntern_returnsTrue() {
        modelManager.addInternship(ALIBABA);
        assertTrue(modelManager.hasInternship(ALIBABA));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void equals() {
        FindMyIntern findMyIntern = new FindMyInternBuilder().withInternship(ALIBABA).withInternship(BINANCE).build();
        FindMyIntern differentFindMyIntern = new FindMyIntern();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(findMyIntern, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(findMyIntern, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different findMyIntern -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFindMyIntern, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALIBABA.getCompany().value.split("\\s+");
        modelManager.updateFilteredInternshipList(new ContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(findMyIntern, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFindMyInternFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(findMyIntern, differentUserPrefs)));
    }
}
