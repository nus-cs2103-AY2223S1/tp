package tuthub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.model.Model.PREDICATE_SHOW_ALL_TUTORS;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.GuiSettings;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.testutil.TuthubBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Tuthub(), new Tuthub(modelManager.getTuthub()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTuthubFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTuthubFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTuthubFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTuthubFilePath(null));
    }

    @Test
    public void setTuthubFilePath_validPath_setsTuthubFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTuthubFilePath(path);
        assertEquals(path, modelManager.getTuthubFilePath());
    }

    @Test
    public void hasTutor_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTutor(null));
    }

    @Test
    public void hasTutor_tutorNotInTuthub_returnsFalse() {
        assertFalse(modelManager.hasTutor(ALICE));
    }

    @Test
    public void hasTutor_tutorInTuthub_returnsTrue() {
        modelManager.addTutor(ALICE);
        assertTrue(modelManager.hasTutor(ALICE));
    }

    @Test
    public void getFilteredTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTutorList().remove(0));
    }

    @Test
    public void equals() {
        Tuthub tuthub = new TuthubBuilder().withTutor(ALICE).withTutor(BENSON).build();
        Tuthub differentTuthub = new Tuthub();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tuthub, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tuthub, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tuthub -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTuthub, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredTutorList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(tuthub, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTuthubFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tuthub, differentUserPrefs)));
    }
}
