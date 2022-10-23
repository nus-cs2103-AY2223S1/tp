package taskbook.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.GuiSettings;
import taskbook.model.person.NameContainsKeywordsPredicate;
import taskbook.model.person.Person;
import taskbook.testutil.Assert;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.TaskBookBuilder;
import taskbook.testutil.TypicalTaskBook;


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
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
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
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTaskBookFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setTaskBookFilePath(null));
    }

    @Test
    public void setTaskBookFilePath_validPath_setsTaskBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaskBookFilePath(path);
        assertEquals(path, modelManager.getTaskBookFilePath());
    }

    @Test
    public void commitTaskBook() {
        assertDoesNotThrow(modelManager::commitTaskBook);
    }

    @Test
    public void canUndoTaskBook_cannotUndo_false() {
        ModelManager model = new ModelManager();
        assertFalse(model.canUndoTaskBook());
    }

    @Test
    public void canUndoTaskBook_canUndo_true() {
        ModelManager model = new ModelManager();
        Person p1 = new PersonBuilder().build();
        model.addPerson(p1);
        model.commitTaskBook();
        assertTrue(model.canUndoTaskBook());
    }

    @Test
    public void undoTaskBook() {
        ModelManager model = new ModelManager();
        Person p1 = new PersonBuilder().build();
        model.addPerson(p1);
        model.commitTaskBook();
        model.undoTaskBook();
        assertEquals(new ModelManager(), model);
    }

    @Test
    public void canRedoTaskBook_cannotRedo_false() {
        ModelManager model = new ModelManager();
        assertFalse(model.canRedoTaskBook());
    }

    @Test
    public void canRedoTaskBook_canRedo_true() {
        ModelManager model = new ModelManager();
        Person p1 = new PersonBuilder().build();
        model.addPerson(p1);
        model.commitTaskBook();
        model.undoTaskBook();
        assertTrue(model.canRedoTaskBook());
    }

    @Test
    public void redoTaskBook() {
        ModelManager model = new ModelManager();
        Person p1 = new PersonBuilder().build();
        model.addPerson(p1);
        model.commitTaskBook();
        model.undoTaskBook();
        model.redoTaskBook();

        ModelManager expected = new ModelManager();
        expected.addPerson(p1);
        expected.commitTaskBook();
        assertEquals(expected, model);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTaskBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(TypicalTaskBook.ALICE));
    }

    @Test
    public void hasPerson_personInTaskBook_returnsTrue() {
        modelManager.addPerson(TypicalTaskBook.ALICE);
        assertTrue(modelManager.hasPerson(TypicalTaskBook.ALICE));
    }

    @Test
    public void findPerson_nullName_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.findPerson(null));
    }

    @Test
    public void findPerson_personNotInTaskBook_returnsNull() {
        assertNull(modelManager.findPerson(TypicalTaskBook.ALICE.getName()));
    }

    @Test
    public void canDeletePerson_personNoTask_returnsTrue() {
        ModelManager model = new ModelManager();
        model.addPerson(TypicalTaskBook.ZED);

        assertTrue(model.canDeletePerson(TypicalTaskBook.ZED));
    }

    @Test
    public void canDeletePerson_personHasTask_returnsFalse() {
        ModelManager model = new ModelManager();
        model.addPerson(TypicalTaskBook.ALICE);
        model.addTask(TypicalTaskBook.EATING);

        assertFalse(model.canDeletePerson(TypicalTaskBook.ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        TaskBook taskBook = new TaskBookBuilder()
                .withPerson(TypicalTaskBook.ALICE).withPerson(TypicalTaskBook.BENSON).build();
        TaskBook differentTaskBook = new TaskBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = TypicalTaskBook.ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonListPredicate(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taskBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonListPredicate(Model.PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskBook, differentUserPrefs)));
    }
}
