package gim.model;

import static gim.model.Model.PREDICATE_SHOW_ALL_EXERCISES;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.ALICE;
import static gim.testutil.TypicalExercises.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import gim.commons.core.GuiSettings;
import gim.model.exercise.NameContainsKeywordsPredicate;
import gim.testutil.ExerciseTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ExerciseTracker(), new ExerciseTracker(modelManager.getExerciseTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setExerciseTrackerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setExerciseTrackerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setExerciseTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setExerciseTrackerFilePath(null));
    }

    @Test
    public void setExerciseTrackerFilePath_validPath_setsExerciseTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setExerciseTrackerFilePath(path);
        assertEquals(path, modelManager.getExerciseTrackerFilePath());
    }

    @Test
    public void hasExercise_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExercise(null));
    }

    @Test
    public void hasExercise_exerciseNotInExerciseTracker_returnsFalse() {
        assertFalse(modelManager.hasExercise(ALICE));
    }

    @Test
    public void hasExercise_exerciseInExerciseTracker_returnsTrue() {
        modelManager.addExercise(ALICE);
        assertTrue(modelManager.hasExercise(ALICE));
    }

    @Test
    public void getFilteredExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExerciseList().remove(0));
    }

    @Test
    public void equals() {
        ExerciseTracker exerciseTracker = new ExerciseTrackerBuilder().withExercise(ALICE).withExercise(BENSON).build();
        ExerciseTracker differentExerciseTracker = new ExerciseTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(exerciseTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(exerciseTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different exerciseTracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentExerciseTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredExerciseList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(exerciseTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setExerciseTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(exerciseTracker, differentUserPrefs)));
    }
}
