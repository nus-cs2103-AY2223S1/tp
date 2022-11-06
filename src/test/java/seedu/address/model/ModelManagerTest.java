package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMMATES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.TASK_TWO;
import static seedu.address.testutil.TypicalTeammates.ALICE;
import static seedu.address.testutil.TypicalTeammates.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.teammate.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TaskPanelBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new TaskPanel(), new TaskPanel(modelManager.getTaskPanel()));
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

    // TODO: Copy next 2 tests for TaskPanel

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
    public void hasTeammate_nullTeammate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTeammate(null));
    }

    @Test
    public void hasTeammate_teammateNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTeammate(ALICE));
    }

    @Test
    public void hasTeammate_teammateInAddressBook_returnsTrue() {
        modelManager.addTeammate(ALICE);
        assertTrue(modelManager.hasTeammate(ALICE));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskPanel_returnsFalse() {
        assertFalse(modelManager.hasTask(TASK_ONE));
    }

    @Test
    public void hasTask_taskInTaskPanel_returnsTrue() {
        modelManager.addTask(TASK_ONE);
        assertTrue(modelManager.hasTask(TASK_ONE));
    }

    @Test
    public void getFilteredTeammateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTeammateList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withTeammate(ALICE).withTeammate(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        TaskPanel taskPanel = new TaskPanelBuilder().withTask(TASK_ONE).withTask(TASK_TWO).build();
        TaskPanel differentTaskPanel = new TaskPanel();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, taskPanel, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, taskPanel, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertNotEquals(modelManager, new ModelManager(differentAddressBook, differentTaskPanel, userPrefs));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredTeammateList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManager(addressBook, taskPanel, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTeammateList(PREDICATE_SHOW_ALL_TEAMMATES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(addressBook, taskPanel, differentUserPrefs));
    }
}
