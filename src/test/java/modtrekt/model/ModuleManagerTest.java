package modtrekt.model;

<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
import static modtrekt.testutil.Assert.assertThrows;
=======
import static modtrekt.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalModules.MA1521;
import static modtrekt.testutil.TypicalModules.MA2001;
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.GuiSettings;
<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
=======
import modtrekt.testutil.ModuleListBuilder;
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java

public class ModuleManagerTest {

    private ModuleManager modelManager = new ModuleManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
        assertEquals(new TaskBook(), new TaskBook(modelManager.getTaskBook()));
=======
        assertEquals(new ModuleList(), new ModuleList(modelManager.getModuleList()));
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
        userPrefs.setTaskBookFilePath(Paths.get("address/book/file/path"));
=======
        userPrefs.setModuleListFilePath(Paths.get("address/book/file/path"));
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
        userPrefs.setTaskBookFilePath(Paths.get("new/address/book/file/path"));
=======
        userPrefs.setModuleListFilePath(Paths.get("new/address/book/file/path"));
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java
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
<<<<<<< HEAD:src/test/java/modtrekt/model/ModelManagerTest.java
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
=======
    public void setModuleListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setModuleListFilePath(null));
    }

    @Test
    public void setModuleListFilePath_validPath_setsModuleListFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setModuleListFilePath(path);
        assertEquals(path, modelManager.getModuleListFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_personNotInModuleList_returnsFalse() {
        assertFalse(modelManager.hasModule(MA1521));
    }

    @Test
    public void hasModule_personInModuleList_returnsTrue() {
        modelManager.addModule(MA1521);
        assertTrue(modelManager.hasModule(MA1521));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        ModuleList addressBook = new ModuleListBuilder().withModule(MA1521).withModule(MA2001).build();
        ModuleList differentModuleList = new ModuleList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModuleManager(addressBook, userPrefs);
        ModuleManager modelManagerCopy = new ModuleManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModuleManager(differentModuleList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModuleListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModuleManager(addressBook, differentUserPrefs)));
    }
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands:src/test/java/modtrekt/model/ModuleManagerTest.java
}
