package seedu.masslinkers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalStudents.ALICE;
import static seedu.masslinkers.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.model.student.DetailsContainsKeywordsPredicate;
import seedu.masslinkers.testutil.MassLinkersBuilder;
//@@author chm252
public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MassLinkers(), new MassLinkers(modelManager.getMassLinkers()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMassLinkersFilePath(Paths.get("masslinkers/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMassLinkersFilePath(Paths.get("new/masslinkers/book/file/path"));
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
    public void setMassLinkersFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMassLinkersFilePath(null));
    }

    @Test
    public void setMassLinkersFilePath_validPath_setsMassLinkersFilePath() {
        Path path = Paths.get("masslinkers/book/file/path");
        modelManager.setMassLinkersFilePath(path);
        assertEquals(path, modelManager.getMassLinkersFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInMassLinkers_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInMassLinkers_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasTelegram_telegramNotInMassLinkers_returnsFalse() {
        assertFalse(modelManager.hasTelegram(BENSON.getTelegram()));
    }

    @Test
    public void hasTelegram_telegramInMassLinkers_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasTelegram(ALICE.getTelegram()));
    }

    @Test
    public void hasGitHub_gitHubNotInMassLinkers_returnsFalse() {
        assertFalse(modelManager.hasGitHub(BENSON.getGitHub()));
    }

    @Test
    public void hasGitHub_gitHubInMassLinkers_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasGitHub(ALICE.getGitHub()));
    }

    @Test
    public void hasEmail_emailNotInMassLinkers_returnsFalse() {
        assertFalse(modelManager.hasEmail(BENSON.getEmail()));
    }

    @Test
    public void hasEmail_emailInMassLinkers_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasEmail(ALICE.getEmail()));
    }

    @Test
    public void hasPhone_phoneNotInMassLinkers_returnsFalse() {
        assertFalse(modelManager.hasPhone(BENSON.getPhone()));
    }

    @Test
    public void hasPhone_phoneInMassLinkers_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasPhone(ALICE.getPhone()));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        MassLinkers massLinkers = new MassLinkersBuilder().withStudent(ALICE).withStudent(BENSON).build();
        MassLinkers differentMassLinkers = new MassLinkers();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(massLinkers, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(massLinkers, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different massLinkers -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentMassLinkers, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new DetailsContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(massLinkers, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMassLinkersFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(massLinkers, differentUserPrefs)));
    }
}
