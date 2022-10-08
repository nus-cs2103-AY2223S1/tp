package modtrekt.model;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.GuiSettings;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskBook(), new TaskBook(modelManager.getTaskBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTaskBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskBookFilePath(null));
    }

    @Test
    public void setTaskBookFilePath_validPath_setsTaskBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaskBookFilePath(path);
        assertEquals(path, modelManager.getTaskBookFilePath());
    }

    //    @Test
    //    public void hasPerson_nullPerson_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    //    }
    //
    //    @Test
    //    public void hasPerson_personNotInAddressBook_returnsFalse() {
    //        assertFalse(modelManager.hasPerson(ALICE));
    //    }
    //
    //    @Test
    //    public void hasPerson_personInAddressBook_returnsTrue() {
    //        modelManager.addPerson(ALICE);
    //        assertTrue(modelManager.hasPerson(ALICE));
    //    }

    //    @Test
    //    public void equals() {
    //        TaskBook addressBook = new AddressBookBuilder().withTas (task1).withTask(BENSON).build();
    //        TaskBook differentAddressBook = new TaskBook();
    //        UserPrefs userPrefs = new UserPrefs();
    //
    //        // same values -> returns true
    //        modelManager = new ModelManager(addressBook, userPrefs);
    //        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
    //        assertTrue(modelManager.equals(modelManagerCopy));
    //
    //        // same object -> returns true
    //        assertTrue(modelManager.equals(modelManager));
    //
    //        // null -> returns false
    //        assertFalse(modelManager.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(modelManager.equals(5));
    //
    //        // different addressBook -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));
    //
    //        // different filteredList -> returns false
    //        String[] keywords = task1.getName().description.split("\\s+");
    //        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    //        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
    //
    //        // resets modelManager to initial state for upcoming tests
    //        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);
    //
    //        // different userPrefs -> returns false
    //        UserPrefs differentUserPrefs = new UserPrefs();
    //        differentUserPrefs.setTaskBookFilePath(Paths.get("differentFilePath"));
    //        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    //    }
}
