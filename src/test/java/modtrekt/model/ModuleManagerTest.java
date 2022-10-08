package modtrekt.model;

import static modtrekt.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalModules.MA1521;
import static modtrekt.testutil.TypicalModules.MA2001;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.GuiSettings;
import modtrekt.testutil.ModuleListBuilder;

public class ModuleManagerTest {

    private ModuleManager modelManager = new ModuleManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ModuleList(), new ModuleList(modelManager.getModuleList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModuleListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModuleListFilePath(Paths.get("new/address/book/file/path"));
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
}
