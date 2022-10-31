package seedu.classify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.classify.commons.core.GuiSettings;
import seedu.classify.model.student.NameContainsKeywordsPredicate;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.StudentRecordBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new StudentRecord(), new StudentRecord(modelManager.getStudentRecord()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setStudentRecordFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setStudentRecordFilePath(Paths.get("new/address/book/file/path"));
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
    public void setStudentRecordFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setStudentRecordFilePath(null));
    }

    @Test
    public void setStudentRecordFilePath_validPath_setsStudentRecordFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setStudentRecordFilePath(path);
        assertEquals(path, modelManager.getStudentRecordFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInStudentRecord_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInStudentRecord_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void isStudentListInfoConcise_initial_returnsFalse() {
        assertFalse(modelManager.isStudentListInfoConcise());
    }

    @Test
    public void toggleStudentListInfoConcise_toggleTrue() {
        modelManager.toggleStudentListInfoConcise();
        assertTrue(modelManager.isStudentListInfoConcise());
    }

    @Test
    public void getFilteredStudents_noStudentsInList() {
        List<Student> li = new ArrayList<>();
        ObservableList<Student> emptyList = FXCollections.observableList(li);
        FilteredStudents filteredStudentsTest = new FilteredStudents(new FilteredList<>(emptyList));
        FilteredStudents filteredStudents = modelManager.getFilteredStudents();

        assertEquals(filteredStudents, filteredStudentsTest);
    }

    @Test void getPrevPredicate_initial() {
        assertEquals(modelManager.getPrevPredicate(), PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Test void storePrevPredicate() {
        Predicate<Student> prevPredicate = x -> false;
        modelManager.storePredicate(prevPredicate);
        assertEquals(modelManager.getPrevPredicate(), prevPredicate);
    }

    @Test
    public void equals() {
        StudentRecord studentRecord = new StudentRecordBuilder().withStudent(ALICE).withStudent(BENSON).build();
        StudentRecord differentStudentRecord = new StudentRecord();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(studentRecord, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(studentRecord, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different studentRecord -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentStudentRecord, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getStudentName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(studentRecord, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setStudentRecordFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(studentRecord, differentUserPrefs)));
    }
}
