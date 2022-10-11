package seedu.rc4hdb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;
import seedu.rc4hdb.testutil.ResidentBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResidentBook(), new ResidentBook(modelManager.getResidentBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setResidentBookFilePath(Paths.get("rc4hdb/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setResidentBookFilePath(Paths.get("new/rc4hdb/book/file/path"));
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
    public void setResidentBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setResidentBookFilePath(null));
    }

    @Test
    public void setResidentBookFilePath_validPath_setsResidentBookFilePath() {
        Path path = Paths.get("rc4hdb/book/file/path");
        modelManager.setResidentBookFilePath(path);
        assertEquals(path, modelManager.getResidentBookFilePath());
    }

    @Test
    public void hasResident_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasResident(null));
    }

    @Test
    public void hasResident_residentNotInResidentBook_returnsFalse() {
        assertFalse(modelManager.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentInResidentBook_returnsTrue() {
        modelManager.addResident(ALICE);
        assertTrue(modelManager.hasResident(ALICE));
    }

    @Test
    public void getFilteredResidentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredResidentList().remove(0));
    }

    @Test
    public void equals() {
        ResidentBook residentBook = new ResidentBookBuilder().withResident(ALICE).withResident(BENSON).build();
        ResidentBook differentResidentBook = new ResidentBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(residentBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(residentBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different residentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentResidentBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredResidentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(residentBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setResidentBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(residentBook, differentUserPrefs)));
    }
}
