package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExams.EXAMONE;
import static seedu.address.testutil.TypicalExams.EXAMTHREE;
import static seedu.address.testutil.TypicalExams.FINAL_EXAM;
import static seedu.address.testutil.TypicalExams.MIDTERM_EXAM;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2030S;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTasks.TASK_A;
import static seedu.address.testutil.TypicalTasks.TASK_B;
import static seedu.address.testutil.TypicalTasks.TASK_C;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.TASK_Q;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.commons.Criteria;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskPresent_returnsTrue() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);
        assertTrue(modelManager.hasTask(TASK_A));
    }

    @Test
    public void hasTask_taskNotPresent_returnsFalse() {
        assertFalse(modelManager.hasTask(TASK_A));
    }

    @Test
    public void hasTaskWithModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTaskWithModule(null));
    }

    @Test
    public void hasTaskWithModule_taskPresent_returnsTrue() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);
        assertTrue(modelManager.hasTaskWithModule(CS2030));
    }

    @Test
    public void hasTaskWithModule_taskNotPresent_returnsFalse() {
        modelManager.addModule(CS2030);
        modelManager.addModule(CS2040);
        modelManager.addTask(TASK_C);
        assertFalse(modelManager.hasTaskWithModule(CS2030));
    }

    @Test
    public void addTask_unfilteredList_success() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);
        assertTrue(modelManager.hasTask(TASK_A));
        assertTrue(modelManager.getFilteredTaskList().contains(TASK_A));
    }

    @Test
    public void addTask_filteredList_success() {
        modelManager.addModule(CS2030);

        // set filtered list which does not include added task
        modelManager.updateFilteredTaskList(prepareTaskPredicate(TASK_B.getDescription().description));

        modelManager.addTask(TASK_A);
        assertTrue(modelManager.hasTask(TASK_A));

        // filtered list should contain added task
        assertTrue(modelManager.getFilteredTaskList().contains(TASK_A));
    }

    @Test
    public void replaceTask_nullFields_throwsNullPointerException() {
        // null target
        assertThrows(NullPointerException.class, () -> modelManager.replaceTask(null, TASK_A, true));

        // null editedTask
        assertThrows(NullPointerException.class, () -> modelManager.replaceTask(TASK_A, null, true));
    }

    @Test
    public void replaceTask_validFields_success() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);

        // check that modelManager contains TASK_A
        assertTrue(modelManager.hasTask(TASK_A));

        modelManager.replaceTask(TASK_A, TASK_B, false);
        assertFalse(modelManager.hasTask(TASK_A));
        assertTrue(modelManager.hasTask(TASK_B));
    }

    @Test
    public void deleteTask_taskPresent_success() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);

        // check that modelManager contains TASK_A
        assertTrue(modelManager.hasTask(TASK_A));

        modelManager.deleteTask(TASK_A);
        assertFalse(modelManager.hasTask(TASK_A));
    }

    @Test
    public void hasExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExam(null));
    }
    @Test
    public void hasExam_examPresent_returnsTrue() {
        modelManager.addExam(EXAMONE);
        assertTrue(modelManager.hasExam(EXAMONE));
    }

    @Test
    public void hasExam_examNotPresent_returnsFalse() {
        assertFalse(modelManager.hasExam(EXAMONE));
    }

    @Test
    public void hasExamWithModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExamWithModule(null));
    }

    @Test
    public void hasExamWithModule_examPresent_returnsTrue() {
        modelManager.addExam(FINAL_EXAM);
        assertTrue(modelManager.hasExamWithModule(CS2030));
    }

    @Test
    public void hasExamWithModule_examNotPresent_returnsFalse() {
        modelManager.addExam(MIDTERM_EXAM);
        assertFalse(modelManager.hasExamWithModule(CS2030));
    }

    @Test
    public void addExam_unfilteredList_success() {
        modelManager.addExam(MIDTERM_EXAM);
        assertTrue(modelManager.hasExam(MIDTERM_EXAM));
    }

    @Test
    public void replaceExam_nullFields_throwsNullPointerException() {
        // null target
        assertThrows(NullPointerException.class, () -> modelManager.replaceExam(null, MIDTERM_EXAM,
                true));

        // null editedExam
        assertThrows(NullPointerException.class, () -> modelManager.replaceExam(MIDTERM_EXAM, null,
                true));
    }

    @Test
    public void replaceExam_validFields_success() {
        modelManager.addExam(MIDTERM_EXAM);

        // check that modelManager contains MIDTERM_EXAM
        assertTrue(modelManager.hasExam(MIDTERM_EXAM));

        modelManager.replaceExam(MIDTERM_EXAM, FINAL_EXAM, false);
        assertFalse(modelManager.hasExam(MIDTERM_EXAM));
        assertTrue(modelManager.hasExam(FINAL_EXAM));
    }

    @Test
    public void deleteExam_examPresent_success() {
        modelManager.addExam(MIDTERM_EXAM);

        // check that modelManager contains TASK_A
        assertTrue(modelManager.hasExam(MIDTERM_EXAM));

        modelManager.deleteExam(MIDTERM_EXAM);
        assertFalse(modelManager.hasExam(MIDTERM_EXAM));
    }

    @Test
    public void addModule_unfilteredList_success() {
        modelManager.addModule(CS2030);
        assertTrue(modelManager.hasModule(CS2030));
        assertTrue(modelManager.getFilteredModuleList().contains(CS2030));
    }

    @Test
    public void addModule_filteredList_success() {
        // set filtered list which does not include added module
        modelManager.updateFilteredModuleList(prepareModulePredicate(CS2040.getModuleCode().moduleCode));

        modelManager.addModule(CS2030);
        assertTrue(modelManager.hasModule(CS2030));

        // filtered list should contain added task
        assertTrue(modelManager.getFilteredModuleList().contains(CS2030));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }
    @Test
    public void hasModule_modulePresent_returnsTrue() {
        modelManager.addModule(CS2030);
        assertTrue(modelManager.hasModule(CS2030));
    }

    @Test
    public void hasModule_moduleNotPresent_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2030));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void updateFilteredModuleList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredModuleList(null));
    }

    @Test
    public void updateFilteredModuleList_validPredicate_success() {
        modelManager.addModule(CS2030);

        // check that original filtered module list contains CS2030
        assertTrue(modelManager.getFilteredModuleList().contains(CS2030));

        modelManager.updateFilteredModuleList(prepareModulePredicate(CS2040.getModuleCode().moduleCode));
        assertFalse(modelManager.getFilteredModuleList().contains(CS2030));
    }

    @Test
    public void deleteModule_modulePresent_success() {
        modelManager.addModule(CS2030);

        // check that modelManager contains CS2030
        assertTrue(modelManager.hasModule(CS2030));

        modelManager.deleteModule(CS2030);
        assertFalse(modelManager.hasModule(CS2030));
    }

    @Test
    public void replaceModule_nullFields_throwsNullPointerException() {
        // null target
        assertThrows(NullPointerException.class, () -> modelManager.replaceModule(null, CS2030));

        // null editedModule
        assertThrows(NullPointerException.class, () -> modelManager.replaceModule(CS2030, null));
    }

    @Test
    public void replaceModule_validFields_success() {
        modelManager.addModule(CS2030);

        // check that modelManager contains CS2030
        assertTrue(modelManager.hasModule(CS2030));

        modelManager.replaceModule(CS2030, CS2040);
        assertFalse(modelManager.hasModule(CS2030));
        assertTrue(modelManager.hasModule(CS2040));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void updateFilteredTaskList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredTaskList(null));
    }

    @Test
    public void updateFilteredTaskList_validPredicate_success() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_A);

        // check that original filtered task list contains TASK_A
        assertTrue(modelManager.getFilteredTaskList().contains(TASK_A));

        modelManager.updateFilteredTaskList(prepareTaskPredicate(TASK_B.getDescription().description));
        assertFalse(modelManager.getFilteredTaskList().contains(TASK_A));
    }

    @Test
    public void sortTaskList_nullCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.sortTaskList(null));
    }

    @Test
    public void sortTaskList_validCriteria_success() {
        modelManager.addModule(CS2030);
        modelManager.addTask(TASK_B);
        modelManager.addTask(TASK_A);

        // check initial order
        assertTrue(modelManager.getFilteredTaskList().get(0).equals(TASK_B));
        assertTrue(modelManager.getFilteredTaskList().get(1).equals(TASK_A));

        modelManager.sortTaskList(new Criteria("description"));
        assertTrue(modelManager.getFilteredTaskList().get(0).equals(TASK_A));
        assertTrue(modelManager.getFilteredTaskList().get(1).equals(TASK_B));
    }

    @Test
    public void unlinkTasksFromExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.unlinkTasksFromExam(null));
    }

    @Test
    public void unlinkTasksFromExam_validExam_success() {
        modelManager.addModule(CS2040);
        modelManager.addModule(CS2030);
        modelManager.addExam(MIDTERM_EXAM);
        modelManager.addExam(FINAL_EXAM);
        modelManager.addTask(TASK_D);
        modelManager.addTask(TASK_Q);

        // check initial link status for both tasks
        assertTrue(modelManager.getFilteredTaskList().get(0).isLinked());
        assertTrue(modelManager.getFilteredTaskList().get(1).isLinked());

        modelManager.unlinkTasksFromExam(MIDTERM_EXAM);

        // check that TASK_D is now unlinked
        assertFalse(modelManager.getFilteredTaskList().get(0).isLinked());

        // check that TASK_Q is still linked
        assertTrue(modelManager.getFilteredTaskList().get(1).isLinked());
    }

    @Test
    public void updateExamFieldForTask_nullFields_throwsNullPointerException() {
        // null previous exam
        assertThrows(NullPointerException.class, () -> modelManager.updateExamFieldForTask(null,
                MIDTERM_EXAM));

        // null new exam
        assertThrows(NullPointerException.class, () -> modelManager.updateExamFieldForTask(MIDTERM_EXAM, null));
    }

    @Test
    public void updateExamFieldForTask_validFields_success() {
        modelManager.addModule(CS2040);
        modelManager.addExam(MIDTERM_EXAM);
        modelManager.addTask(TASK_D);

        // check initial exam of TASK_D
        assertTrue(modelManager.getFilteredTaskList().get(0).getExam().equals(MIDTERM_EXAM));

        modelManager.updateExamFieldForTask(MIDTERM_EXAM, EXAMTHREE);
        assertTrue(modelManager.getFilteredTaskList().get(0).getExam().equals(EXAMTHREE));
    }

    @Test
    public void updateModuleFieldForTask_nullFields_throwsNullPointerException() {
        // null previous module
        assertThrows(NullPointerException.class, () -> modelManager.updateModuleFieldForTask(null, CS2030));

        // null new module
        assertThrows(NullPointerException.class, () -> modelManager.updateModuleFieldForTask(null, CS2030));
    }

    @Test
    public void updateModuleFieldForTask_validFields_success() {
        modelManager.addModule(CS2030);
        modelManager.addModule(CS2030S);
        modelManager.addTask(TASK_A);

        // check initial module of TASK_A
        assertTrue(modelManager.getFilteredTaskList().get(0).getModule().equals(CS2030));

        modelManager.updateModuleFieldForTask(CS2030, CS2030S);
        assertTrue(modelManager.getFilteredTaskList().get(0).getModule().equals(CS2030S));
    }

    @Test
    public void deleteTasksWithModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteTasksWithModule(null));
    }

    @Test
    public void deleteTasksWithModule_validModule_success() {
        modelManager.addModule(CS2030);
        modelManager.addModule(CS2040);
        modelManager.addTask(TASK_A);
        modelManager.addTask(TASK_C);

        // check that initial task list contains TASK_A and TASK_C
        assertTrue(modelManager.hasTask(TASK_A));
        assertTrue(modelManager.hasTask(TASK_C));

        modelManager.deleteTasksWithModule(CS2030);
        assertFalse(modelManager.hasTask(TASK_A));
        assertTrue(modelManager.hasTask(TASK_C));
    }

    @Test
    public void getFilteredExamList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExamList().remove(0));
    }

    @Test
    public void updateFilteredExamList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredExamList(null));
    }

    @Test
    public void isExamLinkedToTask_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.isExamLinkedToTask(null));
    }

    @Test
    public void isExamLinkedToTask_isLinked_returnsTrue() {
        modelManager.addModule(CS2040);
        modelManager.addExam(MIDTERM_EXAM);
        modelManager.addTask(TASK_D);
        assertTrue(modelManager.isExamLinkedToTask(MIDTERM_EXAM));
    }

    @Test
    public void isExamLinkedToTask_isNotLinked_returnsFalse() {
        modelManager.addModule(CS2040);
        modelManager.addExam(MIDTERM_EXAM);
        modelManager.addTask(TASK_D);
        assertFalse(modelManager.isExamLinkedToTask(FINAL_EXAM));
    }

    @Test
    public void updateModuleFieldForExam_nullFields_throwsNullPointerException() {
        // null previous module
        assertThrows(NullPointerException.class, () -> modelManager.updateModuleFieldForExam(null, CS2030));

        // null new module
        assertThrows(NullPointerException.class, () -> modelManager.updateModuleFieldForExam(CS2030, null));
    }

    @Test
    public void updateModuleFieldForExam_validFields_success() {
        modelManager.addModule(CS2030);
        modelManager.addModule(CS2040);
        modelManager.addExam(MIDTERM_EXAM);

        // check initial module of MIDTERM_EXAM
        assertTrue(modelManager.getFilteredExamList().get(0).getModule().equals(CS2040));

        modelManager.updateModuleFieldForExam(CS2040, CS2030);
        assertTrue(modelManager.getFilteredExamList().get(0).getModule().equals(CS2030));
    }

    @Test
    public void deleteExamsWithModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteExamsWithModule(null));
    }

    @Test
    public void deleteExamsWithModule_validModule_success() {
        modelManager.addModule(CS2030);
        modelManager.addModule(CS2040);
        modelManager.addExam(MIDTERM_EXAM);
        modelManager.addExam(FINAL_EXAM);

        // check that exam list contains MIDTERM_EXAM and FINAL_EXAM
        assertTrue(modelManager.hasExam(MIDTERM_EXAM));
        assertTrue(modelManager.hasExam(FINAL_EXAM));

        modelManager.deleteExamsWithModule(CS2030);
        assertTrue(modelManager.hasExam(MIDTERM_EXAM));
        assertFalse(modelManager.hasExam(FINAL_EXAM));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withModule(CS2030).withTask(TASK_A).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate prepareTaskPredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.trim().toLowerCase()));
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate prepareModulePredicate(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(Arrays.asList(userInput.trim().toLowerCase()));
    }
}
