package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskList(), new TaskList(modelManager.getAddressBook()));
        assertEquals(new ArchivedTaskList(), new ArchivedTaskList(modelManager.getArchivedTaskList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setArchivedTaskListFilePath(Paths.get("archivedTask/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        userPrefs.setArchivedTaskListFilePath(Paths.get("archivedTask/book/file/path"));
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
    public void setArchivedTaskBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setArchivedTaskList(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void setArchivedTaskBookFilePath_validPath_setsArchivedTaskBookFilePath() {
        Path path = Paths.get("data/archivedTaskList.json");
        modelManager.setArchivedTaskListFilePath(path);
        assertEquals(path, modelManager.getArchivedTaskListFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasTask_nullArchivedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTaskInArchives(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasTask_taskNotInArchivedTaskBook_returnsFalse() {
        assertFalse(modelManager.hasTaskInArchives(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasTask_taskInArchivedTaskBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        modelManager.archivedTask(ALICE);
        assertTrue(modelManager.hasTaskInArchives(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredArchivedTaskList().remove(0));
    }

    @Test
    public void updateFilterStatus_updatingFilters_isCorrect() {
        // Replaces string when filter is empty.
        modelManager.updateFilterStatus("");
        modelManager.updateFilterStatus("Filter 1");
        assertEquals("Filter 1", modelManager.getFilterStatus());
        // Replaces string shown when filter is "Showing all tasks".
        modelManager.updateFilterStatus("Showing all tasks", true);
        modelManager.updateFilterStatus("Filter 1");
        assertEquals("Filter 1", modelManager.getFilterStatus());
        // Tests adding on a filter.
        modelManager.updateFilterStatus("Additional filter");
        assertEquals("Filter 1, Additional filter", modelManager.getFilterStatus());
        // Tests new filter set when isNewFilterSet is set to true.
        modelManager.updateFilterStatus("Replacement filter", true);
        assertEquals("Replacement filter", modelManager.getFilterStatus());
        // Tests filter set when isNewFilterSet is set to false, even if filter is "Showing all tasks".
        modelManager.updateFilterStatus("Showing all tasks", false);
        assertEquals("Replacement filter, Showing all tasks", modelManager.getFilterStatus());
    }

    @Test
    public void equals() {
        TaskList addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        ArchivedTaskList archivedTaskList = new ArchivedTaskList();
        TaskList differentAddressBook = new TaskList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, archivedTaskList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, archivedTaskList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, archivedTaskList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        modelManager.updateFilteredArchivedTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, archivedTaskList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        differentUserPrefs.setArchivedTaskListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, archivedTaskList, differentUserPrefs)));
    }
}
