package gim.storage;

import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import gim.commons.core.GuiSettings;
import gim.model.ExerciseTracker;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonExerciseTrackerStorage exerciseTrackerStorage = new JsonExerciseTrackerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(exerciseTrackerStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void exerciseTrackerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonExerciseTrackerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonExerciseTrackerStorageTest} class.
         */
        ExerciseTracker original = getTypicalExerciseTracker();
        storageManager.saveExerciseTracker(original);
        ReadOnlyExerciseTracker retrieved = storageManager.readExerciseTracker().get();
        assertEquals(original, new ExerciseTracker(retrieved));
    }

    @Test
    public void getExerciseTrackerFilePath() {
        assertNotNull(storageManager.getExerciseTrackerFilePath());
    }

}
