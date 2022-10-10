package seedu.workbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.ALICE;
import static seedu.workbook.testutil.TypicalInternships.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.testutil.WorkBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new WorkBook(), new WorkBook(modelManager.getWorkBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setWorkBookFilePath(Paths.get("work/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setWorkBookFilePath(Paths.get("new/work/book/file/path"));
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
    public void setWorkBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setWorkBookFilePath(null));
    }

    @Test
    public void setWorkBookFilePath_validPath_setsWorkBookFilePath() {
        Path path = Paths.get("work/book/file/path");
        modelManager.setWorkBookFilePath(path);
        assertEquals(path, modelManager.getWorkBookFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInWorkBook_returnsFalse() {
        assertFalse(modelManager.hasInternship(ALICE));
    }

    @Test
    public void hasInternship_internshipInWorkBook_returnsTrue() {
        modelManager.addInternship(ALICE);
        assertTrue(modelManager.hasInternship(ALICE));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void equals() {
        WorkBook workBook = new WorkBookBuilder().withInternship(ALICE).withInternship(BENSON).build();
        WorkBook differentWorkBook = new WorkBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(workBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(workBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different workBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentWorkBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getCompany().name.split("\\s+");
        modelManager.updateFilteredInternshipList(new CompanyContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(workBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setWorkBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(workBook, differentUserPrefs)));
    }
}
