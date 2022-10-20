package seedu.phu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.AMAZON;
import static seedu.phu.testutil.TypicalInternships.BYTEDANCE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.GuiSettings;
import seedu.phu.model.internship.NameContainsKeywordsPredicate;
import seedu.phu.testutil.InternshipBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager;

    @BeforeEach
    public void setUp() {
        modelManager = new ModelManager();
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternshipBook(), new InternshipBook(modelManager.getInternshipBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternshipBookFilePath(Paths.get("internship/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternshipBookFilePath(Paths.get("new/internship/book/file/path"));
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
    public void setInternshipBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternshipBookFilePath(null));
    }

    @Test
    public void setInternshipBookFilePath_validPath_setsInternshipBookFilePath() {
        Path path = Paths.get("internship/book/file/path");
        modelManager.setInternshipBookFilePath(path);
        assertEquals(path, modelManager.getInternshipBookFilePath());
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipBook_returnsFalse() {
        assertFalse(modelManager.hasInternship(AMAZON));
    }

    @Test
    public void hasInternship_internshipInInternshipBook_returnsTrue() {
        modelManager.addInternship(AMAZON);
        assertTrue(modelManager.hasInternship(AMAZON));
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void canUndo_internshipBookIsNotUndoable_returnsFalse() {
        assertFalse(modelManager.canUndoInternshipBook());
    }

    @Test
    public void canUndo_internshipBookIsUndoable_returnsTrue() {
        modelManager.addInternship(AMAZON);
        modelManager.commitInternshipBookChange();
        assertTrue(modelManager.canUndoInternshipBook());
    }

    @Test
    public void undo_internshipBookIsUndoable_success() {
        modelManager.addInternship(AMAZON);
        modelManager.commitInternshipBookChange();
        modelManager.undoInternshipBook();
        assertFalse(modelManager.hasInternship(AMAZON));
    }

    @Test
    public void canRedo_internshipBookIsNotRedoable_returnsFalse() {
        assertFalse(modelManager.canRedoInternshipBook());
    }

    @Test
    public void canRedo_internshipBookIsRedoable_returnsTrue() {
        modelManager.addInternship(AMAZON);
        modelManager.commitInternshipBookChange();
        modelManager.undoInternshipBook();
        assertTrue(modelManager.canRedoInternshipBook());
    }

    @Test
    public void redo_internshipBookIsRedoable_success() {
        modelManager.addInternship(AMAZON);
        modelManager.commitInternshipBookChange();
        modelManager.undoInternshipBook();
        modelManager.redoInternshipBook();
        assertTrue(modelManager.hasInternship(AMAZON));
    }

    @Test
    public void equals() {
        InternshipBook internshipBook = new InternshipBookBuilder().withInternship(AMAZON)
                .withInternship(BYTEDANCE).build();
        InternshipBook differentInternshipBook = new InternshipBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internshipBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internshipBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internshipBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternshipBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = AMAZON.getName().fullName.split("\\s+");
        modelManager.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(internshipBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternshipBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internshipBook, differentUserPrefs)));
    }
}
