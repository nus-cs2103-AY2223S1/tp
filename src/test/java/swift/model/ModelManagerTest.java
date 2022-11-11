package swift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.model.Model.PREDICATE_SHOW_ALL_PEOPLE;
import static swift.testutil.Assert.assertThrows;
import static swift.testutil.TypicalPersons.ALICE;
import static swift.testutil.TypicalPersons.BENSON;
import static swift.testutil.TypicalTasks.BUY_MILK;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.commons.core.GuiSettings;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.PersonNameContainsKeywordsPredicate;
import swift.testutil.AddressBookBuilder;
import swift.testutil.PersonBuilder;
import swift.testutil.TaskBuilder;
import swift.testutil.TypicalBridges;

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
    public void getFilteredBridgeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredBridgeList().remove(0));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTask(BUY_MILK));
    }

    @Test
    public void hasTask_taskInAddressBook_returnsTrue() {
        modelManager.addTask(BUY_MILK);
        assertTrue(modelManager.hasTask(BUY_MILK));
    }

    @Test
    public void hasBridge_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasBridge(null));
    }

    @Test
    public void hasBridge_taskNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasBridge(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void hasBridge_bridgeInAddressBookAddBridge_returnsTrue() {
        modelManager.addBridge(TypicalBridges.DEFAULT_BRIDGE_1);
        assertTrue(modelManager.hasBridge(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void hasBridge_bridgeInAddressBookAddPersonTask_returnsTrue() {
        modelManager.addBridge(
                TypicalBridges.getTypicalAddressBook().getPersonList().get(0),
                TypicalBridges.getTypicalAddressBook().getTaskList().get(0));
        assertTrue(modelManager.hasBridge(new PersonTaskBridge(
                UUID.fromString(PersonBuilder.DEFAULT_UUID), UUID.fromString(TaskBuilder.DEFAULT_ID))));
    }

    @Test
    public void addBridge_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addBridge(null));
    }

    @Test
    public void addBridge_nullPersonAndTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addBridge(null, null));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
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

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PEOPLE);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
