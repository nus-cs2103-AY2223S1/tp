package seedu.taassist.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taassist.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.taassist.testutil.Assert.assertThrows;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;
import static seedu.taassist.testutil.TypicalStudents.ALICE;
import static seedu.taassist.testutil.TypicalStudents.BENSON;
import static seedu.taassist.testutil.TypicalStudents.CARL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.model.student.NameContainsKeywordsPredicate;
import seedu.taassist.testutil.TaAssistBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaAssist(), new TaAssist(modelManager.getTaAssist()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaAssistFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaAssistFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTaAssistFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaAssistFilePath(null));
    }

    @Test
    public void setTaAssistFilePath_validPath_setsTaAssistFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaAssistFilePath(path);
        assertEquals(path, modelManager.getTaAssistFilePath());
    }

    @Test
    public void setTaAssist_inFocusMode_exitsFocusMode() {
        modelManager.addModuleClass(CS1231S);
        modelManager.enterFocusMode(CS1231S);
        modelManager.setTaAssist(new TaAssist());
        assertFalse(modelManager.isInFocusMode());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTaAssist_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTaAssist_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void setFilteredListPredicate_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFilteredListPredicate(null));
    }

    @Test
    public void setFilteredListPredicate_validPredicate_setsFilteredListPredicate() {
        modelManager.addStudent(ALICE);
        modelManager.addStudent(BENSON);
        modelManager.setFilteredListPredicate(new NameContainsKeywordsPredicate(List.of("A")));
        assertEquals(List.of(ALICE), modelManager.getFilteredStudentList());
    }

    @Test
    public void andFilteredListPredicate_noPreviousPredicate_setsFilteredListPredicate() {
        modelManager.addStudent(ALICE);
        modelManager.addStudent(BENSON);
        modelManager.andFilteredListPredicate(new NameContainsKeywordsPredicate(List.of("A")));
        assertEquals(List.of(ALICE), modelManager.getFilteredStudentList());
    }

    @Test
    public void andFilteredListPredicate_hasPreviousPredicate_setsFilteredListPredicate() {
        modelManager.addStudent(ALICE);
        modelManager.addStudent(BENSON);
        modelManager.addStudent(CARL);
        modelManager.setFilteredListPredicate(new NameContainsKeywordsPredicate(List.of("A")));
        assertEquals(List.of(ALICE, CARL), modelManager.getFilteredStudentList());
        modelManager.andFilteredListPredicate(new NameContainsKeywordsPredicate(List.of("R")));
        assertEquals(List.of(CARL), modelManager.getFilteredStudentList());
    }

    @Test
    public void equals() {
        TaAssist taAssist = new TaAssistBuilder().withStudent(ALICE).withStudent(BENSON).build();
        TaAssist differentTaAssist = new TaAssist();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taAssist, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taAssist, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taAssist -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaAssist, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.setFilteredListPredicate(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taAssist, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.setFilteredListPredicate(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaAssistFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taAssist, differentUserPrefs)));
    }
}
