package jeryl.fyp.model;

import static jeryl.fyp.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.BENSON;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
import static jeryl.fyp.testutil.TypicalStudents.DANIEL;
import static jeryl.fyp.testutil.TypicalStudents.ELLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentNameContainsKeywordsPredicate;
import jeryl.fyp.testutil.FypManagerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FypManager(), new FypManager(modelManager.getFypManager()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFypManagerFilePath(Paths.get("jeryl/fyp/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFypManagerFilePath(Paths.get("new/jeryl/fyp/file/path"));
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
    public void setFypManagerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFypManagerFilePath(null));
    }

    @Test
    public void setFypManagerFilePath_validPath_setsFypManagerFilePath() {
        Path path = Paths.get("jeryl/fyp/file/path");
        modelManager.setFypManagerFilePath(path);
        assertEquals(path, modelManager.getFypManagerFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInFypManager_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInFypManager_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void getUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            modelManager.addStudent(student);
        }
        assertEquals(modelManager.getUncompletedStudentList().get(0), ALICE);
        assertEquals(modelManager.getUncompletedStudentList().get(1), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getUncompletedStudentList().get(2));
    }

    @Test
    public void getCompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            modelManager.addStudent(student);
        }
        assertEquals(modelManager.getCompletedStudentList().get(0), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getCompletedStudentList().get(1));
    }

    @Test
    public void getSortedByProjectNameUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, CARL, DANIEL };
        for (Student student : students) {
            modelManager.addStudent(student);
        }
        assertEquals(modelManager.getSortedByProjectNameUncompletedStudentList().get(0), CARL);
        assertEquals(modelManager.getSortedByProjectNameUncompletedStudentList().get(1), BENSON);
        assertEquals(modelManager.getSortedByProjectNameUncompletedStudentList().get(2), ALICE);
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager
                .getSortedByProjectNameUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedByProjectStatusUncompletedStudentList() {
        Student[] students = { CARL, BENSON, DANIEL, ALICE };
        for (Student student : students) {
            modelManager.addStudent(student);
        }
        assertEquals(modelManager.getSortedByProjectStatusUncompletedStudentList().get(0), CARL);
        assertEquals(modelManager.getSortedByProjectStatusUncompletedStudentList().get(1), ALICE);
        assertEquals(modelManager.getSortedByProjectStatusUncompletedStudentList().get(2), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager
                .getSortedByProjectStatusUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedCompletedStudentList() {
        Student[] students = { BENSON, ALICE, DANIEL, ELLE };
        for (Student student : students) {
            modelManager.addStudent(student);
        }
        assertEquals(modelManager.getSortedCompletedStudentList().get(0), ELLE);
        assertEquals(modelManager.getSortedCompletedStudentList().get(1), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.getSortedCompletedStudentList().get(2));
    }

    @Test
    public void equals() {
        FypManager fypManager = new FypManagerBuilder().withStudent(ALICE).withStudent(BENSON).build();
        FypManager differentFypManager = new FypManager();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(fypManager, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(fypManager, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different fypManager -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFypManager, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getStudentName().fullStudentName.split("\\s+");
        modelManager.updateFilteredStudentList(new StudentNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(fypManager, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFypManagerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(fypManager, differentUserPrefs)));
    }
}
