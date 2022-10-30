package seedu.boba.model;

import org.junit.jupiter.api.Test;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.model.customer.NameContainsKeywordsPredicate;
import seedu.boba.testutil.BobaBotBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.model.BobaBotModel.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.ALICE;
import static seedu.boba.testutil.TypicalCustomers.BENSON;

public class BobaBotModelManagerTest {

    private BobaBotModelManager modelManager = new BobaBotModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new BobaBot(), new BobaBot(modelManager.getBobaBot()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setBobaBotFilePath(Paths.get("boba/bot/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setBobaBotFilePath(Paths.get("new/boba/bot/file/path"));
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
    public void setBobaBotFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setBobaBotFilePath(null));
    }

    @Test
    public void setBobaBotFilePath_validPath_setsBobaBotFilePath() {
        Path path = Paths.get("boba/bot/file/path");
        modelManager.setBobaBotFilePath(path);
        assertEquals(path, modelManager.getBobaBotFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInBobaBot_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInBobaBot_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        BobaBot bobaBot = new BobaBotBuilder().withPerson(ALICE).withPerson(BENSON).build();
        BobaBot differentBobaBot = new BobaBot();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new BobaBotModelManager(bobaBot, userPrefs);
        BobaBotModelManager modelManagerCopy = new BobaBotModelManager(bobaBot, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different bobaBot -> returns false
        assertFalse(modelManager.equals(new BobaBotModelManager(differentBobaBot, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new BobaBotModelManager(bobaBot, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setBobaBotFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new BobaBotModelManager(bobaBot, differentUserPrefs)));
    }
}
